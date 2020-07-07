package com.zb.service.impl;

import com.zb.pojo.Store;
import com.zb.service.ElSearchStoreService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElSearchStoreServiceImpl implements ElSearchStoreService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public List<Store> elSearchStore(String keyWord) throws Exception {

        List<Store> storeList = new ArrayList<>();


        //创建查询请求对象
        SearchRequest searchRequest = new SearchRequest("my-communtiy");
        searchRequest.types("doc");
        //构建查询方式
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //因为是多个条件的组合创建bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (keyWord != null && !"".equals(keyWord)) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyWord, new String[]{"storeName", "desc"})
                    .operator(Operator.OR).field("storeName", 10)
            );

        }
        //创建高亮对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签
        highlightBuilder.preTags("<span style='color:red';>");
        highlightBuilder.postTags("</span>");
        //设置那些字段需要高亮
        highlightBuilder.fields().add(new HighlightBuilder.Field("storeName"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("desc"));
        //将highlightBuilder添加到查询对象中去
        searchSourceBuilder.highlighter(highlightBuilder);
        //绑定查询构建对象
        searchSourceBuilder.query(boolQueryBuilder);
        //将构建对象存储到request对象中去
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit : hitsHits) {
            String ids = hit.getId();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Integer id = Integer.parseInt(sourceAsMap.get("id").toString());
            String storeName = sourceAsMap.get("storeName").toString();
            String desc = sourceAsMap.get("desc").toString();
            String rule = sourceAsMap.get("rule").toString();
            Integer storeType=null;
            if(sourceAsMap.get("storeType")!=null){
                storeType = Integer.parseInt(sourceAsMap.get("storeType").toString());

            }

            String address = sourceAsMap.get("address").toString();
            Double score=0.0;
            if(sourceAsMap.get("score")!=null){
                score = Double.parseDouble(sourceAsMap.get("score").toString());
            }

            String imgUrl = sourceAsMap.get("imgUrl").toString();
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            if (highlightFieldMap != null) {
                HighlightField field = highlightFieldMap.get("storeName");
                if (field != null) {
                    Text[] storeNameTexts = field.getFragments();
                    StringBuffer storeNameSbf = new StringBuffer();
                    for (Text text : storeNameTexts) {
                        storeNameSbf.append(text);
                    }
                    storeName = storeNameSbf.toString();
                }

                HighlightField field1 = highlightFieldMap.get("desc");
                if (field1 != null) {
                    Text[] descTexts = field1.getFragments();
                    StringBuffer descSbf = new StringBuffer();
                    for (Text text : descTexts) {
                        descSbf.append(text);
                    }
                    desc = descSbf.toString();
                }

            }

            Store store = new Store();
            store.setImgurl(imgUrl);
            store.setAddress(address);
            store.setDesc(desc);
            store.setStoreName(storeName);
            store.setId(id);
            store.setRule(rule);
            store.setStoreType(storeType);
            if (score != null) {
                store.setScore(new BigDecimal(score));
            } else {
                store.setScore(null);
            }


            storeList.add(store);
        }


        return storeList;
    }
}
