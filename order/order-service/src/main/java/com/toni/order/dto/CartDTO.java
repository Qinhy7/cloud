package com.toni.order.dto;

import lombok.Data;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 17:55
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
