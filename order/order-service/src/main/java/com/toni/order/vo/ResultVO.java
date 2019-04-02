package com.toni.order.vo;

import lombok.Data;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 10:13
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}
