package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class CommunityTaskHis implements Serializable {
    //任务id
    private String id;
    //
    private String createTime;
    //
    private String updateTime;
    //
    private String deleteTime;
    //任务类型
    private String taskType;
    //交换机名称
    private String mqExchange;
    //routingkey
    private String mqRoutingkey;
    //任务请求的内容
    private String requestBody;
    //乐观锁版本号
    private Integer version;
    //任务状态
    private String status;
    //
    private String errormsg;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setCreateTime (String  createTime){
        this.createTime=createTime;
    }
    public  String getCreateTime(){
        return this.createTime;
    }
    public void setUpdateTime (String  updateTime){
        this.updateTime=updateTime;
    }
    public  String getUpdateTime(){
        return this.updateTime;
    }
    public void setDeleteTime (String  deleteTime){
        this.deleteTime=deleteTime;
    }
    public  String getDeleteTime(){
        return this.deleteTime;
    }
    public void setTaskType (String  taskType){
        this.taskType=taskType;
    }
    public  String getTaskType(){
        return this.taskType;
    }
    public void setMqExchange (String  mqExchange){
        this.mqExchange=mqExchange;
    }
    public  String getMqExchange(){
        return this.mqExchange;
    }
    public void setMqRoutingkey (String  mqRoutingkey){
        this.mqRoutingkey=mqRoutingkey;
    }
    public  String getMqRoutingkey(){
        return this.mqRoutingkey;
    }
    public void setRequestBody (String  requestBody){
        this.requestBody=requestBody;
    }
    public  String getRequestBody(){
        return this.requestBody;
    }
    public void setVersion (Integer  version){
        this.version=version;
    }
    public  Integer getVersion(){
        return this.version;
    }
    public void setStatus (String  status){
        this.status=status;
    }
    public  String getStatus(){
        return this.status;
    }
    public void setErrormsg (String  errormsg){
        this.errormsg=errormsg;
    }
    public  String getErrormsg(){
        return this.errormsg;
    }
}
