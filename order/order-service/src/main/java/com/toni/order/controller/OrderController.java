package com.toni.order.controller;

import com.toni.order.converter.OrderForm2OrderDTOConverter;
import com.toni.order.service.OrderService;
import com.toni.order.utils.ResultVOUtil;
import com.toni.order.vo.ResultVO;
import com.toni.order.dto.OrderDTO;
import com.toni.order.enums.ResultEnum;
import com.toni.order.exception.OrderException;
import com.toni.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:32
 */
@RestController
@Slf4j
@RequestMapping("/com/toni/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult){
        /**
         * 1. 校验表单数据
         * 2. 调用商品服务获取购买商品信息
         * 3. 计算商品总价
         * 4. 调用商品服务减少对应库存
         * 5. 进行订单入库操作
         */
        //1.
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //2. 将orderForm对象转换为orderDTO对象
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        // 3. 4 5
        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVOUtil.getResultVO(map);
    }

    @PostMapping("/finish")
    public ResultVO finish(@RequestParam("orderId") String orderId){
        return ResultVOUtil.getResultVO(orderService.finish(orderId));
    }

}
