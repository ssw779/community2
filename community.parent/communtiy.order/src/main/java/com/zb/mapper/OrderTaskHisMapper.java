package com.zb.mapper;

import com.zb.pojo.CommunityTask;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderTaskHisMapper {

    public CommunityTask getCommunityTaskHisById(@Param("id") String id);

    public List<CommunityTask> getCommunityTaskHisListByMap(Map<String,Object> param);



    public Integer insertCommunityTaskHis(CommunityTask community);

    public Integer updateCommunityTaskHis(CommunityTask community);
}
