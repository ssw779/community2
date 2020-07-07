package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class Goodstype  {
    //
    private Integer id;
    //
    private String typeName;
    //
    private Integer parentId;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setTypeName (String  typeName){
        this.typeName=typeName;
    }
    public  String getTypeName(){
        return this.typeName;
    }
    public void setParentId (Integer  parentId){
        this.parentId=parentId;
    }
    public  Integer getParentId(){
        return this.parentId;
    }
}
