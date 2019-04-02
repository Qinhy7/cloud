package com.toni.order.exception;

import com.toni.order.enums.ResultEnum;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:38
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException (Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public OrderException (ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
