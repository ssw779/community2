package com.zb.pojo;
import java.io.Serializable;

/***
*   实时库存表
*/
public class TempGoods implements Serializable {
    //
    private Long id;
    //店铺编号
    private Integer storeId;
    //商品id
    private Long goodsId;
    //记录时间
    private String recordDate;
    //状态（预定状态1：预定、2：已支付：3：30分钟后未支付）
    private Integer status;
    //哪个用户
    private String createdBy;
    //
    private String modifyDate;
    //
    private Long modifiedBy;
    //get set 方法
    public void setId (Long  id){
        this.id=id;
    }
    public  Long getId(){
        return this.id;
    }
    public void setStoreId (Integer  storeId){
        this.storeId=storeId;
    }
    public  Integer getStoreId(){
        return this.storeId;
    }
    public void setGoodsId (Long  goodsId){
        this.goodsId=goodsId;
    }
    public  Long getGoodsId(){
        return this.goodsId;
    }
    public void setRecordDate (String  recordDate){
        this.recordDate=recordDate;
    }
    public  String getRecordDate(){
        return this.recordDate;
    }
    public void setStatus (Integer  status){
        this.status=status;
    }
    public  Integer getStatus(){
        return this.status;
    }
    public void setCreatedBy (String  createdBy){
        this.createdBy=createdBy;
    }
    public  String getCreatedBy(){
        return this.createdBy;
    }
    public void setModifyDate (String  modifyDate){
        this.modifyDate=modifyDate;
    }
    public  String getModifyDate(){
        return this.modifyDate;
    }
    public void setModifiedBy (Long  modifiedBy){
        this.modifiedBy=modifiedBy;
    }
    public  Long getModifiedBy(){
        return this.modifiedBy;
    }
}
