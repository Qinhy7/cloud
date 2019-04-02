package com.toni.order.enums;

import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 9:58
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
            ;
    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
