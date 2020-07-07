package com.zb.mapper;

import com.zb.pojo.CommunityPurchaserecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityPurchaserecordMapper {

	public CommunityPurchaserecord getCommunityPurchaserecordById(@Param(value = "id") Long id);

	public List<CommunityPurchaserecord>	getCommunityPurchaserecordListByMap(Map<String,Object> param);



	public Integer insertCommunityPurchaserecord(CommunityPurchaserecord communityPurchaserecord);

	public Integer updateCommunityPurchaserecord(CommunityPurchaserecord communityPurchaserecord);



}
