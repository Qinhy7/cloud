package com.toni.order.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * stream 接收消息
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 9:24
 */
@Component
@EnableBinding(StreamClient.class)// 绑定
@Slf4j
public class StreamReceiver {

    // 指定Stream监听的队列
    @StreamListener(StreamClient.MEG)
    public void process(Object message){
        log.info("StramReceiver: {}",message);
    }


}
