package com.toni.userservice.enums;

import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 18:32
 */
@Getter
public enum ResultEnum {
    USER_NOT_EXIST(1, "用户不存在"),
    USER_ROLE_ERROR(2,"用户权限不对")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
