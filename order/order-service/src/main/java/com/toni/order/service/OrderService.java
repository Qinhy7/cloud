package com.toni.order.service;


import com.toni.order.dto.OrderDTO;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:49
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);


}
