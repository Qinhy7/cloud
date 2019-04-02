package com.toni.order.repository;

import com.toni.order.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:31
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> getOrderDetailsByOrderId(String orderId);

}
