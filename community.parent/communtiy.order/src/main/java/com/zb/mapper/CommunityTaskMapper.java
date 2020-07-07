package com.zb.mapper;

import com.zb.pojo.CommunityTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityTaskMapper {

	public CommunityTask getCommunityTaskById(@Param(value = "id") String id);

	public Integer insertCommunityTask(CommunityTask communityTask);

	/**
	 * 添加历史任务
	 * @param communityTask
	 * @return
	 */
	public Integer insertCommunityTaskHis(CommunityTask communityTask);

	public Integer updateCommunityTask(CommunityTask communityTask);

	/**
	 * 修改历史任务
	 * @param communityTask
	 * @return
	 */
	public Integer updateCommunityTaskHis(CommunityTask communityTask);

	/**
	 * 修改任务表的修改时间为现在
	 * @param id
	 * @return
	 */
	public int updateTaskTime(@Param("id") String id);

	/**
	 * 获取任务表一分钟以前的数据
	 * @return
	 */
	public List<CommunityTask>getAMinuteAgoTask();

	/**
	 * 乐观锁：修改版本号
	 * @param communityTask
	 * @return
	 */
	public int updateTaskversion(CommunityTask communityTask);

	/**
	 * 删除任务根据编号
	 * @param id
	 * @return
	 */
	public int delTask(@Param("id") String id);
}
