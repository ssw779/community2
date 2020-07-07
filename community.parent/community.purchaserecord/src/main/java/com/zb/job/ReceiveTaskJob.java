package com.zb.job;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.pojo.CommunityTask;
import com.zb.pojo.CommunityTaskHis;
import com.zb.service.PurchaseRecordService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiveTaskJob {

    @Autowired
    private PurchaseRecordService purchaseRecordService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 监听orderServer发过来的队列，存放的是任务表里的信息
     *
     * @param communityTask MQ发过来的对象
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_ADDCHOOSECOURSE)
    public void receive(CommunityTask communityTask, Message message, Channel channel) {
        System.out.println("选择商品模块接收到消息队列中的数据");
        //存进itrip_purchaserecord_db的记录表里去
        //获取任务表里requestBody里面的信息，应该实一个完整购买记录对应：PurchaseRecord
        //添加成功以后，发送一个消息队列给orderServer，删除对应的记录
        if (purchaseRecordService.ddChooseGoods(communityTask)) {
            amqpTemplate.convertAndSend(RabbitMQConfig.EX_LEARNING_ADDCHOOSECOURSE, RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE_KEY, communityTask);
            System.out.println("添加成功");
        }


    }
}
