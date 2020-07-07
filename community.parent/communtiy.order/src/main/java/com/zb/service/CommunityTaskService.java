package com.zb.service;

import com.zb.pojo.CommunityTask;

import java.util.List;

public interface CommunityTaskService {





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
    public int delTask(String id);


    /**
     * 修改任务表的修改时间为现在
     * @param id
     * @return
     */
    public int updateTaskTime(String id);

    /**
     * 寻找一分钟以前的任务
     * @return
     */
    public List<CommunityTask>getAminuteAgoTask();

    /**
     * 根据编号查找任务
     * @param id
     * @return
     */
    public CommunityTask getCommunityTaskById(String id);


    /**临时任务表
     * 修改
     * @param communityTask
     * @return
     */
    public int updateTask(CommunityTask communityTask);

    /**
     * 添加临时库存表
     * @param communityTask
     * @return
     */
    public int insertTask(CommunityTask communityTask);
}
