package com.zb.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 *商品POJO类
 * @author 孙硕威
 */
public class Goods implements Serializable {
    //商品编号
    private Integer id;
    //店铺编号
    private Integer storeId;
    //店铺类型1：普通商家，2：家政服务类
    private Integer storeType;
    //店铺名称
    private String storeName;
    //商品名称
    private String goodsName;
    //商品介绍
    private String goodsDesc;
    //商品品牌
    private String brand;
    //商品价格
    private BigDecimal goodsPrice;
    //是否是热门商品1：热门2：非热门
    private Integer isHotGoods;
    //销量
    private Integer salesvolume;
    //库存
    private Integer repertory;
    //商品类型
    private Integer goodsType;
    //取货方式1：快递2：到店自取,3不限
    private Integer takesType;
    //商品评分
    private BigDecimal score;
    //点击量
    private Integer checkCount;
    //是否参与活动，1：参与2：不参与
    private Integer isPromotion;
    //商品状态1：在售2：下架
    private Integer shopStatus;
    //上架时间
    private String putawayTime;
    //购买须知
    private String matter;
    //图片地址
    private String imgUrl;
    //起始位
    private Integer index;
    //每页显示几条数据
    private Integer size;
    //起步价
    private Integer startPrice;
    //最高价
    private Integer endPrice;

    //get set 方法


    public Integer getSalesvolume() {
        return salesvolume;
    }

    public void setSalesvolume(Integer salesvolume) {
        this.salesvolume = salesvolume;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreId() {
        return this.storeId;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getStoreType() {
        return this.storeType;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDesc() {
        return this.goodsDesc;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setIsHotGoods(Integer isHotGoods) {
        this.isHotGoods = isHotGoods;
    }

    public Integer getIsHotGoods() {
        return this.isHotGoods;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Integer getRepertory() {
        return this.repertory;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() {
        return this.goodsType;
    }

    public void setTakesType(Integer takesType) {
        this.takesType = takesType;
    }

    public Integer getTakesType() {
        return this.takesType;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return this.score;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    public Integer getCheckCount() {
        return this.checkCount;
    }

    public void setIsPromotion(Integer isPromotion) {
        this.isPromotion = isPromotion;
    }

    public Integer getIsPromotion() {
        return this.isPromotion;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getShopStatus() {
        return this.shopStatus;
    }

    public void setPutawayTime(String putawayTime) {
        this.putawayTime = putawayTime;
    }

    public String getPutawayTime() {
        return this.putawayTime;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getMatter() {
        return this.matter;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", storeType=" + storeType +
                ", storeName='" + storeName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", brand='" + brand + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", isHotGoods=" + isHotGoods +
                ", repertory=" + repertory +
                ", goodsType=" + goodsType +
                ", takesType=" + takesType +
                ", score=" + score +
                ", checkCount=" + checkCount +
                ", isPromotion=" + isPromotion +
                ", shopStatus=" + shopStatus +
                ", putawayTime='" + putawayTime + '\'' +
                ", matter='" + matter + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", index=" + index +
                ", size=" + size +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                '}';
    }
}
