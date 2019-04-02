package com.toni.product.enums;

import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 18:03
 */
@Getter
public enum ProductErrorEnums {

    PRODUCT_NOT_EXITS(1, "商品不存"),
    PRODUCT_STOCK_EMPTY(2,"库存异常")
        ;

    private Integer code;
    private String msg;

    private ProductErrorEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
