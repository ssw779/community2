package com.zb.vo;

import com.zb.pojo.Image;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品VO类
 *
 * @author 孙硕威
 */
public class StoreVo implements Serializable {
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
    //图片类型 是店铺还是商品 还是评论图片
    private Integer imageType;
    //图片是哪家店铺的，哪个商品的
    private Integer targetId;
    //图片显示位置
    private Integer imagePostion;
    //起始位置
    private Integer index;
    //每页显示几条数据
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getIsHotStore() {
        return isHotStore;
    }

    public void setIsHotStore(Integer isHotStore) {
        this.isHotStore = isHotStore;
    }

    public Integer getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(Integer isPromotion) {
        this.isPromotion = isPromotion;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Image> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<Image> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getImagePostion() {
        return imagePostion;
    }

    public void setImagePostion(Integer imagePostion) {
        this.imagePostion = imagePostion;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getShopParenType() {
        return shopParenType;
    }

    public void setShopParenType(Integer shopParenType) {
        this.shopParenType = shopParenType;
    }
}
