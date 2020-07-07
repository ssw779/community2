package com.zb.tools;

import com.zb.feign.StoreFeignClient;
import com.zb.pojo.Store;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Estools {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private StoreFeignClient storeFeignClient;


    /**
     * 创建索引，创建映射
     *
     * @throws Exception
     */
    public void addIndex() throws Exception {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("my-communtiy");
        //参数
        createIndexRequest.settings(
                Settings.builder().put("number_of_shards", 1).
                        put("number_of_replicas", 0).
                        build());

        //创建映射
        createIndexRequest.mapping("doc", "  {\n" +
                "\t\"properties\": {\n" +
                "\t\t\"id\": {\n" +
                "\t\t\t\"type\": \"integer\"\n" +
                "\t\t},\n" +
                "\t\t\"storeName\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "            \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"desc\" :{\n" +
                "\t\t    \"type\":\"text\",\n" +
                "\t\t     \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"rule\":{\n" +
                "\t\t    \"type\":\"text\"\n" +
                "\t\t},\n" +
                "\t\t\n" +
                "\t\t\"storeType\": {\n" +
                "\t\t\t\"type\": \"text\"\n" +
                "\t\t},\n" +
                "\t\t\"address\": {\n" +
                "\t\t\t\"type\": \"text\"\n" +
                "\t\t},\n" +
                "\t\t\"score\": {\n" +
                "\t\t\t\"type\": \"float\"\n" +
                "\t\t},\n" +
                "\t\t\"imgUrl\" :{\n" +
                "\t\t\t\"type\": \"text\", \n" +
                "\t\t\t\"index\":false\n" +
                "\t\t\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t\t\n" +
                "}", XContentType.JSON);

        //获取索引对象
        IndicesClient indices = client.indices();
        //创建并返回一个响应对象
        CreateIndexResponse indexResponse = indices.create(createIndexRequest);
        //是否执行成功
        boolean val = indexResponse.isAcknowledged();
        System.out.println("执行结果" + val);

    }

    /**
     * 添加文档
     */
    public void addDoc() throws Exception{
        List<Store> storeList = storeFeignClient.elsGetStore();
        for (int i = 0; i < storeList.size(); i++) {
            Store store=new Store();
            store=storeList.get(i);
            Map<String, Object> data = new HashMap<>();
            data.put("id",store.getId() );
            data.put("storeName",store.getStoreName());
            data.put("desc",store.getDesc() );
            data.put("rule",store.getRule() );
            data.put("storeType",store.getShopType() );
            data.put("address",store.getAddress() );
            data.put("score",store.getScore() );
            data.put("imgUrl",store.getImgurl() );
            //创建请求对象
            IndexRequest indexRequest = new IndexRequest("my-communtiy", "doc",  store.getId()+ "");
            //绑定数据
            indexRequest.source(data);
            //执行获取响应
            IndexResponse indexResponse = client.index(indexRequest);
            DocWriteResponse.Result result = indexResponse.getResult();
        }

    }
}
