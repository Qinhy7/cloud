package com.toni.order.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.toni.order.bean.ProductInfo;
import com.toni.order.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 11:58
 */
@Component
@Slf4j
public class ProductReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = {@Queue("productInfo")})
    public void process(String msg){
        List<ProductInfo> productInfoList = (List<ProductInfo>) JsonUtil.fromJson(msg,
                new TypeReference<List<ProductInfo>>(){});
        log.info("接收到信息："+productInfoList);

        // 将库存存放到redis中
        for (ProductInfo productInfo : productInfoList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
                    productInfo.getProductStock().toString());
        }
    }

}
