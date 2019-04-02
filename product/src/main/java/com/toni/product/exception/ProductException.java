package com.toni.product.exception;

import com.toni.product.enums.ProductErrorEnums;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 18:01
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ProductException(ProductErrorEnums errorEnums){
        super(errorEnums.getMsg());
        this.code = errorEnums.getCode();
    }

}
