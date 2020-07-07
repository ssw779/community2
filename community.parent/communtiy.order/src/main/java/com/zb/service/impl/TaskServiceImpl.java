package com.zb.service.impl;

import com.zb.mapper.CommunityTaskMapper;
import com.zb.mapper.OrderTaskHisMapper;
import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import com.zb.service.TaskService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskServiceImpl implements TaskService {
    @Autowired
    private CommunityTaskMapper communityTaskMapper;
    @Autowired
    private OrderTaskHisMapper orderTaskHisMapper;
    @Autowired
    private CommunityTaskService communityTaskService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<CommunityTask> getAMinuteAgoTask() {
        return communityTaskService.getAminuteAgoTask();
    }

    @Override
    public void publishtask(CommunityTask communityTask) {
        CommunityTask task = communityTaskService.getCommunityTaskById(communityTask.getId());
        if (task != null) {
            System.out.println("有任务执行发送任务");
            //发送任务
            amqpTemplate.convertAndSend(task.getMqExchange(), task.getMqRoutingkey(), task);
            //修改任务表的时间，把时间修改为now（），为的是如果发送失败，不会立马再次发送，会等待一分钟
            communityTaskService.updateTaskTime(task.getId());
        }
    }

    @Override
    public int getTaskLock(String id, Integer version) {
        CommunityTask communityTask = new CommunityTask();
        communityTask.setId(id);
        communityTask.setVersion(version);

        if (communityTaskService.updateTaskversion(communityTask) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public void finshTask(CommunityTask communityTask) {
        System.out.println("执行结束任务");
        //添加信息到历史任务
        System.out.println(communityTask.getId());
        String id=communityTask.getId();
        CommunityTask communityTaskById = orderTaskHisMapper.getCommunityTaskHisById(id);
        int count=0;
        if (communityTaskById == null) {
            //不存在就添加
            count=orderTaskHisMapper.insertCommunityTaskHis(communityTask);
        } else {
        //存在就修改
           count= orderTaskHisMapper.updateCommunityTaskHis(communityTask);
        }
        //删除任务
       if(count>0){
           communityTaskService.delTask(communityTask.getId());
       }

        //存在就修改，不存在就添加
        //添加成功后删除

    }
}
