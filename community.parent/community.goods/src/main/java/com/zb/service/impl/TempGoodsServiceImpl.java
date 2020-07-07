package com.zb.service.impl;

import com.zb.mapper.TempGoodsMapper;
import com.zb.pojo.TempGoods;
import com.zb.service.TempGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempGoodsServiceImpl implements TempGoodsService {
    @Autowired
    private TempGoodsMapper tempGoodsMapper;


    @Override
    public int updateTempGoods(TempGoods tempGoods) {
        return tempGoodsMapper.updateCommunityTempGoods(tempGoods);
    }

    @Override
    public TempGoods getTempGoods(TempGoods tempGoods) {
        return tempGoodsMapper.getTempGoods(tempGoods);
    }
}
