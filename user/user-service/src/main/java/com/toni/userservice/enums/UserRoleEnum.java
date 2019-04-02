package com.toni.userservice.enums;

import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 18:36
 */
@Getter
public enum UserRoleEnum {
    BUYER(1, "买家端"),
    SELL(2,"卖家端")
    ;

    private Integer code;
    private String msg;

    UserRoleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
