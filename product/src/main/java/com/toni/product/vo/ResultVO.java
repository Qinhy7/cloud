package com.toni.product.vo;

import lombok.Data;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:57
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

}
