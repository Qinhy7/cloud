package com.toni.order.repository;

import com.toni.order.bean.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:30
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
