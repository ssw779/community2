package com.zb.service;

import com.zb.pojo.Image;
import com.zb.vo.ImageVo;

import java.util.List;
import java.util.Map;

public interface ImageService {
    /**
     * 多条件查找图片
     * @param imageVo
     * @return
     * @throws Exception
     */
    public List<Image> getImageListByVo(ImageVo imageVo)throws Exception;
}
