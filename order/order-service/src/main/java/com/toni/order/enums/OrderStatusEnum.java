package com.toni.order.enums;

import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:57
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
