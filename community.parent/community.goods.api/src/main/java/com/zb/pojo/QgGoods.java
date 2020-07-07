package com.zb.pojo;
import java.io.Serializable;

/***
*   抢购表
*/
public class QgGoods implements Serializable {
    //编号
    private Integer id;
    //商品编号
    private Integer  goodsNo;
    //商品名称
    private String  goodsName;
    //店铺名称
    private Integer storeNo;
    //库存
    private Integer inventory;
    //get set 方法

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Integer goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
