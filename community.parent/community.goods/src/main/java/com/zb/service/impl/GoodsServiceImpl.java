package com.zb.service.impl;

import com.zb.dto.Page;
import com.zb.mapper.GoodsMapper;
import com.zb.mapper.ImageMapper;
import com.zb.pojo.Goods;
import com.zb.pojo.Image;
import com.zb.service.GoodsService;
import com.zb.util.PageUtil;
import com.zb.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public PageUtil<Goods> findAllGoodsByVo(Goods goods) throws Exception {
        PageUtil<Goods> pageUtil = new PageUtil<>();
        Integer count = goodsMapper.getGoodsCount(goods);
        if (goods.getIndex() != null && goods.getIndex() != null) {

            goods.setIndex((goods.getIndex() - 1) * goods.getSize());
        }

        List<Goods> goodsList = goodsMapper.getGoodsList(goods);
        if (goodsList != null) {
            //查询某个店家每个商品的图片
            for (Goods goods1 : goodsList) {
                ImageVo imageVo = new ImageVo();
                //商品编号
                imageVo.setTargetId(goods1.getId());
                //图片类型
                imageVo.setType(2);
                List<Image> imageList = imageMapper.getImageListByMap(imageVo);
                String imgUrl = null;
                for (int i = 0; i < imageList.size(); i++) {
                    imgUrl = imageList.get(0).getImgUrl();
                }
                goods1.setImgUrl(imgUrl);
            }

        }

        pageUtil.setSize(goods.getSize());
        pageUtil.setIndex(goods.getIndex());
        pageUtil.setData(goodsList);
        pageUtil.setCount(count);
        return pageUtil;
    }

    @Override
    public int updateGoods(Goods goods) throws Exception {

        return goodsMapper.updateGoods(goods);
    }
}
