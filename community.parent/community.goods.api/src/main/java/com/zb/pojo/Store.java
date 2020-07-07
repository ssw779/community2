package com.zb.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 商铺实体类
 *
 * @author 孙硕威
 */
public class Store {
    //编号
    private Integer id;
    //店铺名
    private String storeName;
    //店铺类型1：普通商家2：家政类商家
    private Integer storeType;
    //商品类型，1：美食，2：甜点饮品，3水果生鲜，4蔬菜，5干货坚果，6零食
    private Integer shopParenType;
    //火锅，海鲜，烧烤之类的分类，
    private Integer shopType;
    //地址
    private String address;
    //城市编号
    private Integer cityId;
    //描述
    private String desc;
    //店铺介绍
    private String rule;
    //评分
    private BigDecimal score;
    //是否是热门商家，1：热门2：非热门
    private Integer isHotStore;
    //是不是促销商品
    private Integer isPromotion;
    //是否付钱上推荐位
    private Integer isPay;
    //推荐开始时间
    private String startTime;
    //推荐结束时间
    private String endTime;
    //图片地址
    private List<Image> imgUrl;
    //单个图片地址
    private String imgurl;

    //get set 方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getStoreType() {
        return this.storeType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Integer getShopType() {
        return this.shopType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return this.rule;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return this.score;
    }

    public void setIsHotStore(Integer isHotStore) {
        this.isHotStore = isHotStore;
    }

    public Integer getIsHotStore() {
        return this.isHotStore;
    }

    public void setIsPromotion(Integer isPromotion) {
        this.isPromotion = isPromotion;
    }

    public Integer getIsPromotion() {
        return this.isPromotion;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsPay() {
        return this.isPay;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public List<Image> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<Image> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getShopParenType() {
        return shopParenType;
    }

    public void setShopParenType(Integer shopParenType) {
        this.shopParenType = shopParenType;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
