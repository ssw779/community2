package com.zb.mapper;

import com.zb.pojo.CommunityTaskHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityTaskHisMapper {

	public CommunityTaskHis getCommunityTaskHisById(@Param(value = "id") Long id);

	public List<CommunityTaskHis>	getCommunityTaskHisListByMap(Map<String,Object> param);



	public Integer insertCommunityTaskHis(CommunityTaskHis communityTaskHis);

	public Integer updateCommunityTaskHis(CommunityTaskHis communityTaskHis);



}
