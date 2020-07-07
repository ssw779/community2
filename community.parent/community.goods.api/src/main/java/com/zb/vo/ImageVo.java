package com.zb.vo;

import java.io.Serializable;

/**
 * 商品Vo类
 * @author 孙硕威
 */
public class ImageVo implements Serializable {

    //图片编号
    private Integer id;
    //图片类型，1：店铺2：商品3：评论
    private Integer type;
    //哪个店铺的图片
    private Integer targetId;
    //图片地址
    private String imgUrl;
    //图片位置1：首页展示
    private Integer postion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPostion() {
        return postion;
    }

    public void setPostion(Integer postion) {
        this.postion = postion;
    }
}
