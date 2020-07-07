package com.zb.tools;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

@Component
public class CanalTools {

    @Autowired
    private RestHighLevelClient client;

    //添加
    public void execution() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmtryCount = 1200;
            while (emptyCount < totalEmtryCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
//                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    private void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                    addEsData(rowData.getAfterColumnsList());
                } else {
                    //是这个表“tb_content”才能执行
                    if (entry.getHeader().getTableName().equals("tb_content")) {
                        updateRedisData(rowData.getAfterColumnsList());
                    }
                    if (entry.getHeader().getTableName().equals("store")) {
                        //修改elsearch数据
                        updateESData(rowData.getAfterColumnsList());
                        //updateRecommendRedisData(rowData.getAfterColumnsList());

                    }

                }
            }
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 修改canal轮询
     * @param columns
     */
    private void updateRedisData(List<Column> columns) {
        Set<Integer> categoryId = new HashSet<>();

        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
            if (column.getName().equals("category_id")) {
                categoryId.add(Integer.parseInt(column.getValue()));
            }
            //获取到编号然后一次调用nginx的请求，把id传进去 lua
            for (Integer integer : categoryId) {
                String url = "http://localhost:9000/bannerUpdate?id=" + integer;
                String result = restTemplate.getForObject(url, String.class);
                System.out.println("远程调用nginx中的接口程序:" + result);
            }


        }

    }

    /**
     * 同步修改店铺信息 调用lua文件
     *
     * @param columns
     */
    public void updateRecommendRedisData(List<Column> columns) {
        String[] arry = new String[2];

        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());

            if (column.getName().equals("storeType")) {
                arry[0] = column.getValue();
            }
            if (column.getName().equals("shopParenType")) {

                arry[1] = column.getValue();
            }


        }
        System.out.println(arry[0] + "\t" + arry[1]);
        String url = "http://localhost:9000/recUpdate?storeType=" + arry[0] + "&shopParenType=" + arry[1];
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("远程调用nginx中的接口程序:" + result);

    }

    /**
     * 修改elSearch中的数据
     *
     * @param columns
     */
    private void updateESData(List<Column> columns) {
        System.out.println("同步修改数据");
        try {
            Map<String, Object> data = new HashMap<>();
            String id = "";
            for (Column column : columns) {
                if (column.getName().equals("id")) {
                    id = column.getValue();
                    continue;
                }
                data.put(column.getName(), column.getValue());
            }
            UpdateRequest updateRequest = new UpdateRequest("my-communtiy", "doc", id);
            updateRequest.doc(data);
            UpdateResponse updateResponse = client.update(updateRequest);
            DocWriteResponse.Result result = updateResponse.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步添加elSearch中的数据
     * @param columns
     */
    public void addEsData(List<Column> columns) {
        System.out.println(columns);
        System.out.println("同步添加数据");

        try {
            Map<String, Object> data = new HashMap<>();
            System.out.println(data);
            Integer id = null;
            String storeName = "";
            String desc = "";
            String rule = "";
            Integer storeType = null;
            String address = "";
            Double score = 0.0;
            String imgUrl = "";
            for (Column column : columns) {
                if (column.getName().equals("id")) {
                    id = Integer.parseInt(column.getValue());

                }
                if (column.getName().equals("storeName")) {
                    storeName = column.getValue();

                }
                if (column.getName().equals("desc")) {
                    desc = column.getValue();

                }
                if (column.getName().equals("rule")) {
                    rule = column.getValue();

                }
                if (column.getName().equals("storeType")) {
                    storeType = Integer.parseInt(column.getValue());

                }
                if (column.getName().equals("address")) {
                    address = column.getValue();

                }
                if (column.getName().equals("score")) {
                    score = Double.parseDouble(column.getValue());

                }
                if (column.getName().equals("imgUrl")) {
                    imgUrl = column.getValue();

                }
                data.put(column.getName(), column.getValue());

            }
            System.out.println(data);
            IndexRequest indexRequest = new IndexRequest("my-communtiy", "doc", id+"");
            //绑定数据
            indexRequest.source(data);
            //执行获取响应
            IndexResponse indexResponse = client.index(indexRequest);
            DocWriteResponse.Result responseResult = indexResponse.getResult();
            System.out.println(responseResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
        }
    }
}
