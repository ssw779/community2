package com.zb.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 *
 */
public class Orders implements Serializable{
    //订单编号
    private Integer id;
    //用户编号
    private Integer userId;
    //商品类型
    private Integer goodsType;
    //商品编号
    private Integer goodsNo;
    //商品名
    private String goodsName;
    //订单号
    private String orderNo;
    //交易编号
    private String tardeNo;
    //商家编号
    private Integer storeNo;
    //商家名
    private String storeName;
    //购买数量
    private Integer count;
    //下订单时间
    private String createTime;
    //订单结束时间
    private String finshTime;
    //1：支付宝2：微信3：现金
    private Integer payType;
    //支付金额
    private BigDecimal payAmount;
    //订单状态1待支付2：已完成3：已取消
    private Integer orderStatus;
    //最后修改时间
    private String modifiTime;
    //銷量
    private Integer salesVolume;

    //get set 方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsNo(Integer goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Integer getGoodsNo() {
        return this.goodsNo;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setTardeNo(String tardeNo) {
        this.tardeNo = tardeNo;
    }

    public String getTardeNo() {
        return this.tardeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getStoreNo() {
        return this.storeNo;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setFinshTime(String finshTime) {
        this.finshTime = finshTime;
    }

    public String getFinshTime() {
        return this.finshTime;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayType() {
        return this.payType;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() {
        return this.payAmount;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setModifiTime(String modifiTime) {
        this.modifiTime = modifiTime;
    }

    public String getModifiTime() {
        return this.modifiTime;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}
