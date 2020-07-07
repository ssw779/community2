package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.CommunityPurchaserecordMapper;
import com.zb.mapper.CommunityTaskHisMapper;
import com.zb.pojo.CommunityPurchaserecord;
import com.zb.pojo.CommunityTask;
import com.zb.pojo.CommunityTaskHis;
import com.zb.service.PurchaseRecordService;
import com.zb.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    @Autowired
    private CommunityPurchaserecordMapper communityPurchaserecordMapper;


    @Autowired
    private CommunityTaskHisMapper communityTaskHisMapper;


    @Override
    public boolean ddChooseGoods(CommunityTask communityTask) {
        try {
            //itrip_purchaserecord
            //从communityTask的requestBody取出订单的完整信息
            Map<String, Object> param = new HashMap<>();
            CommunityPurchaserecord purchaserecord = JSON.parseObject(communityTask.getRequestBody(), CommunityPurchaserecord.class);
            param.put("goodsId", purchaserecord.getGoodsId());
            param.put("userId", purchaserecord.getUserId());
            //先判断这个表里有没有数据，有就修改，没有就添加
            List<CommunityPurchaserecord> purchaserecordList = communityPurchaserecordMapper.getCommunityPurchaserecordListByMap(param);
            CommunityPurchaserecord communityPurchaserecord = null;
            if (purchaserecordList.size() > 0) {
                communityPurchaserecord = purchaserecordList.get(0);
            }
            //之前没有购买记录
            if (communityPurchaserecord == null) {
                communityPurchaserecord = new CommunityPurchaserecord();
                //添加
                communityPurchaserecord.setId(IdWorker.getId());
                communityPurchaserecord.setCharge(purchaserecord.getCharge());
                communityPurchaserecord.setEndTime(purchaserecord.getEndTime());
                communityPurchaserecord.setGoodsId(purchaserecord.getGoodsId());
                communityPurchaserecord.setPrice(purchaserecord.getPrice());
                communityPurchaserecord.setStartTime(purchaserecord.getStartTime());
                communityPurchaserecord.setStatus(communityTask.getStatus());
                communityPurchaserecord.setUserId(purchaserecord.getUserId());
                communityPurchaserecord.setValid(purchaserecord.getValid());

                communityPurchaserecordMapper.insertCommunityPurchaserecord(communityPurchaserecord);
            } else {
                //修改
                //修改起始时间和结束时间，修改状态
                communityPurchaserecord = new CommunityPurchaserecord();
                communityPurchaserecord.setId(IdWorker.getId());
                communityPurchaserecord.setCharge(purchaserecord.getCharge());
                communityPurchaserecord.setEndTime(purchaserecord.getEndTime());
                communityPurchaserecord.setGoodsId(purchaserecord.getGoodsId());
                communityPurchaserecord.setPrice(purchaserecord.getPrice());
                communityPurchaserecord.setStartTime(purchaserecord.getStartTime());
                communityPurchaserecord.setStatus(2 + "");
                communityPurchaserecord.setUserId(purchaserecord.getUserId());
                communityPurchaserecord.setValid(purchaserecord.getValid());
                communityPurchaserecordMapper.updateCommunityPurchaserecord(communityPurchaserecord);
            }


            //qg_task_his
            List<CommunityTaskHis> communityTaskHisList = communityTaskHisMapper.getCommunityTaskHisListByMap(param);
            CommunityTaskHis communityTaskHis=null;
            if(communityTaskHisList.size()>0){
                communityTaskHis=communityTaskHisList.get(0);
            }
            //没有记录
            if(communityTaskHis==null){
                communityTaskHis=new CommunityTaskHis();
                communityTaskHis.setCreateTime(communityTask.getCreateTime());
                communityTaskHis.setDeleteTime(communityTask.getDeleteTime());
                communityTaskHis.setErrormsg(communityTask.getErrormsg());
                communityTaskHis.setId(communityTask.getId());
                communityTaskHis.setMqExchange(communityTask.getMqExchange());
                communityTaskHis.setMqRoutingkey(communityTask.getMqRoutingkey());
                communityTaskHis.setRequestBody(communityTask.getRequestBody());
                communityTaskHis.setStatus(communityTask.getStatus());
                communityTaskHis.setTaskType(communityTask.getTaskType());
                communityTaskHis.setUpdateTime(communityTask.getUpdateTime());
                communityTaskHis.setVersion(communityTask.getVersion());
                communityTaskHisMapper.insertCommunityTaskHis(communityTaskHis);
            }else{
                //修改
                communityTaskHis=new CommunityTaskHis();
                communityTaskHis.setCreateTime(communityTask.getCreateTime());
                communityTaskHis.setDeleteTime(communityTask.getDeleteTime());
                communityTaskHis.setErrormsg(communityTask.getErrormsg());
                communityTaskHis.setId(communityTask.getId());
                communityTaskHis.setMqExchange(communityTask.getMqExchange());
                communityTaskHis.setMqRoutingkey(communityTask.getMqRoutingkey());
                communityTaskHis.setRequestBody(communityTask.getRequestBody());
                communityTaskHis.setStatus(communityTask.getStatus());
                communityTaskHis.setTaskType(communityTask.getTaskType());
                communityTaskHis.setUpdateTime(communityTask.getUpdateTime());
                communityTaskHis.setVersion(communityTask.getVersion());
                communityTaskHisMapper.updateCommunityTaskHis(communityTaskHis);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
