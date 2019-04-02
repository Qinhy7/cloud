package com.toni.order.controller;

import com.toni.order.listener.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * stream 发送消息测试
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 9:29
 */
@RestController
public class StreamMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        // 使用streamClient 发送消息
        streamClient.output().send(MessageBuilder.withPayload("hello").build());
    }



}
