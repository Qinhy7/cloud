package com.toni.order.service.impl;

import com.toni.order.bean.ProductInfo;
import com.toni.order.enums.OrderStatusEnum;
import com.toni.order.bean.OrderMaster;
import com.toni.order.client.ProductClient;
import com.toni.order.dto.CartDTO;
import com.toni.order.bean.OrderDetail;
import com.toni.order.dto.OrderDTO;
import com.toni.order.enums.PayStatusEnum;
import com.toni.order.enums.ResultEnum;
import com.toni.order.exception.OrderException;
import com.toni.order.repository.OrderDetailRepository;
import com.toni.order.repository.OrderMasterRepository;
import com.toni.order.service.OrderService;
import com.toni.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:50
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        /**
         * 2. 调用商品服务获取购买商品信息
         * 3. 计算商品总价
         * 4. 调用商品服务减少对应库存
         * 5. 进行订单入库操作
         */

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        // 2.
        List<String> strings = orderDetailList
                .stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.getProductInfoByProductId(strings);

        //3.
        BigDecimal sum = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDetailList) {
            for (ProductInfo productInfo : productInfoList) {
                if(orderDetail.getProductId().equals(productInfo.getProductId())){
                    // 计算价格
                    sum = new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice()).add(sum);

                   // 给orderDetail赋值，方便入库
                   BeanUtils.copyProperties(productInfo, orderDetail);
                   orderDetail.setDetailId(KeyUtil.genUniqueKey());
                   orderDetail.setOrderId(orderId);
                   // 详情表入库
                   orderDetailRepository.save(orderDetail);
                }
            }
        }

        //4
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.reduceStock(cartDTOList);

        // 5 主表入库
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(sum);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        // 查询数据库
        Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findById(orderId);
        if(optionalOrderMaster.isPresent()){
            throw new OrderException(ResultEnum.ORDER_EMPTY);
        }

        // 判断订单状态
        OrderMaster orderMaster = optionalOrderMaster.get();
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW)){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.getOrderDetailsByOrderId(orderId);
        if(orderDetailList == null && orderDetailList.size() <= 0){
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        // 返回结果
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
