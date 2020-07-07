package com.zb.pojo;

import java.io.Serializable;

/***
 *
 */
public class Image implements Serializable {
    //
    private Integer id;
    //图片类型，1：店铺2：商品3：评论
    private Integer type;
    //哪个店铺的图片
    private Integer targetId;
    //图片地址
    private String imgUrl;
    //图片位置1：首页展示
    private Integer postion;

    //get set 方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetId() {
        return this.targetId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setPostion(Integer postion) {
        this.postion = postion;
    }

    public Integer getPostion() {
        return this.postion;
    }
}
