package com.toni.product.enums;

import lombok.Getter;

/**
 * 商品信息状态
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:43
 */
@Getter
public enum  ProductInfoEnum {

    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    ProductInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
