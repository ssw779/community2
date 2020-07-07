package com.zb.job;

import com.netflix.discovery.converters.Auto;
import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import com.zb.service.TaskService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskJob {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CommunityTaskService communityTaskService;


    @Scheduled(cron = "0/3 * * * * *")
    public void send() {
        //获取到数据库一分钟以前的数据
        List<CommunityTask> aminuteAgoTask = communityTaskService.getAminuteAgoTask();
        for (CommunityTask communityTask : aminuteAgoTask) {

            //如果获取到锁
            if(taskService.getTaskLock(communityTask.getId(),communityTask.getVersion())>0){
                //就发送任务
                taskService.publishtask(communityTask);
            }
        }
    }

    /**
     * 监听recordConsumer发送过来的队列消息
     * @param communityTask
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE)
    public void finshTask(CommunityTask communityTask, Message message, Channel channel){
        System.out.println("监听完成队列");
        taskService.finshTask(communityTask);

    }

}
