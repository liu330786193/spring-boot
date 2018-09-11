package com.yongjun.stock.service.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created by lyl on 2017/10/26.
 */
@Service
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("消息消费者 = " + message.toString());
    }
}
