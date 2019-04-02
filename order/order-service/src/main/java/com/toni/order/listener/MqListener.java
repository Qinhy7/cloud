package com.toni.order.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 消息的监听类
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 8:26
 */
@Component
@Slf4j
public class MqListener {

//    @RabbitListener(queues = "myQueue") // 这创建的myQueue需要在mq中先定义好
//    @RabbitListener(queuesToDeclare = {@Queue ("myQueue")}) // 这个会在使用时自动定义
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
//            key = "order" // 使用key来指定接收消息的分类，表明只接收order类的消息，对应的在发送消息时也得指定发送消息的类型
    )}) // 这样在定义queue时也会定义exchange
    public void listen(String msg){
        log.info(msg);
    }

}
