package com.yongjun.stock.service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lyl on 2017/10/26.
 */
@Service
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendQueue(String exchange_key, String queue_key, Object object){
        System.out.println("生产了数据!");
        amqpTemplate.convertAndSend(exchange_key, queue_key, object);
    }


}
