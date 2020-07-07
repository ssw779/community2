package com.zb.service.impl;

import com.zb.mapper.ImageMapper;
import com.zb.pojo.Image;
import com.zb.service.ImageService;
import com.zb.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<Image> getImageListByVo(ImageVo imageVo) throws Exception {
        return imageMapper.getImageListByMap(imageVo);
    }
}
