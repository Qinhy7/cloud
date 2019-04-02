package com.toni.order.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 8:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqListenerTest {

    @Autowired
    private AmqpTemplate amqpTemplate; // 发送测试消息

    @Test
    public void test(){
        amqpTemplate.convertAndSend("myQueue","now is "+new Date());
    }

}
