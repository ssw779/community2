package com.zb.service;

import com.zb.pojo.CommunityTask;

import java.util.List;

public interface TaskService {
    /**
     * 查找一分钟以前的任务
     * 为什么要查找一分钟以前的任务，因为如果发送消息失败，不会立即发送，要发送一分钟以前的数据
     *
     * @return
     */
    public List<CommunityTask> getAMinuteAgoTask();

    /**
     * 发送任务到消息队列
     *
     * @param communityTask
     */
    public void publishtask(CommunityTask communityTask);


    /**
     * 获取锁:CAS
     *  为什么要获取锁：
     *  因为多线程并发时，有可能导致任务重复发送，所以需要加锁，谁获取到锁谁有权限发送任务
     * @param id
     * @param version
     * @return return 1:获得锁，可以操作，return 0未获得锁
     */
    public int getTaskLock(String id, Integer version);

    /**
     * 结束任务
     * @param communityTask
     */
    public void finshTask(CommunityTask communityTask);
}
