package com.zb.pojo;
import java.io.Serializable;
import java.math.BigDecimal;

/***
*   
*/
public class CommunityPurchaserecord implements Serializable {
    //
    private String id;
    //商品id
    private String goodsId;
    //用户id
    private String userId;
    //收费规则
    private String charge;
    //课程价格
    private BigDecimal price;
    //有效性
    private String valid;
    //
    private String startTime;
    //
    private String endTime;
    //状态
    private String status;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setGoodsId (String  goodsId){
        this.goodsId=goodsId;
    }
    public  String getGoodsId(){
        return this.goodsId;
    }
    public void setUserId (String  userId){
        this.userId=userId;
    }
    public  String getUserId(){
        return this.userId;
    }
    public void setCharge (String  charge){
        this.charge=charge;
    }
    public  String getCharge(){
        return this.charge;
    }
    public void setPrice (BigDecimal  price){
        this.price=price;
    }
    public  BigDecimal getPrice(){
        return this.price;
    }
    public void setValid (String  valid){
        this.valid=valid;
    }
    public  String getValid(){
        return this.valid;
    }
    public void setStartTime (String  startTime){
        this.startTime=startTime;
    }
    public  String getStartTime(){
        return this.startTime;
    }
    public void setEndTime (String  endTime){
        this.endTime=endTime;
    }
    public  String getEndTime(){
        return this.endTime;
    }
    public void setStatus (String  status){
        this.status=status;
    }
    public  String getStatus(){
        return this.status;
    }
}
