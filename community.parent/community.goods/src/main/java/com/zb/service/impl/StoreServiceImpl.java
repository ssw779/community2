package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.ImageMapper;
import com.zb.mapper.StoreMapper;
import com.zb.pojo.Image;
import com.zb.pojo.Store;
import com.zb.service.StoreService;
import com.zb.vo.ImageVo;
import com.zb.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Store> elsGetStore() {
        return storeMapper.elsGetStore();
    }

    @Override
    public List<Store> getStoreListByVo(StoreVo storeVo) throws Exception {
        //redis的key 根据店铺的类型，图片显示的位置、店铺的分类父级编号、店铺分类子级编号来区分
        String key = "storeList" + storeVo.getImagePostion() + storeVo.getShopType() + storeVo.getShopParenType() + storeVo.getStoreType();
        //存进redis
        //获取到要查询的店铺
        List<Store> storeList = null;
        if (redisTemplate.hasKey(key)) {
            System.out.println("从redis拿数据");
            String jsonToStr = redisTemplate.opsForValue().get(key).toString();
            storeList = JSON.parseArray(jsonToStr, Store.class);
        } else {

            storeList = storeMapper.getStoreListByMap(storeVo);
            for (Store store : storeList) {
                ImageVo imageVo = new ImageVo();
                //哪个店铺
                imageVo.setTargetId(store.getId());
                //什么类型
                imageVo.setType(storeVo.getImageType());
                //什么位置
                imageVo.setPostion(storeVo.getImagePostion());
                List<Image> imageList = imageMapper.getImageListByMap(imageVo);
                store.setImgUrl(imageList);
            }
            String toJson = JSON.toJSONString(storeList);
            redisTemplate.opsForValue().set(key, toJson, 60, TimeUnit.SECONDS);

        }

        return storeList;
    }

    @Override
    public Store getStoreById(Integer id) throws Exception {
        Store store = storeMapper.getStoreById(id);
        ImageVo imageVo = new ImageVo();
        //图片位置2店铺首页
        imageVo.setPostion(2);
        //图片类型
        imageVo.setType(1);
        imageVo.setTargetId(store.getId());
        List<Image> imageListByMap = imageMapper.getImageListByMap(imageVo);
        store.setImgUrl(imageListByMap);
        return store;
    }
}
