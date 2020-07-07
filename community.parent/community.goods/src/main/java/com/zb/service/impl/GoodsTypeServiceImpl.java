package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.GoodsTypeMapper;
import com.zb.pojo.Goodstype;
import com.zb.pojo.Store;
import com.zb.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<Goodstype> getShoptypeListByPojo(Goodstype goodstype) throws Exception {
        String key = "shopType" + goodstype.getParentId();
        List<Goodstype> storeList = new ArrayList<>();
        if (redisTemplate.hasKey(key)) {
            System.out.println("从redis拿数据");
            String jsonToStr = redisTemplate.opsForValue().get(key).toString();
            storeList = JSON.parseArray(jsonToStr, Goodstype.class);

        } else {
            System.out.println("从数据库拿数据");
            storeList = goodsTypeMapper.getShoptypeListByPojo(goodstype);
            String toJson = JSON.toJSONString(storeList);
            redisTemplate.opsForValue().set(key, toJson);
        }

        return storeList;

    }
}
