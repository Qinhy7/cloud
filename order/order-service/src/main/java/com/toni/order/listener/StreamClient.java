package com.toni.order.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * stream 客户端
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 9:20
 */
public interface StreamClient {

    String MEG = "myMessage";

    @Input(StreamClient.MEG)
    SubscribableChannel input();

    @Output(StreamClient.MEG)
    MessageChannel output();

}
