package com.toni.product.service;

import com.toni.product.bean.ProductInfo;
import com.toni.product.dto.CartDTO;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:39
 */
public interface ProductInfoService {

    List<ProductInfo> getAllUp();

    List<ProductInfo> getAllByProductIdIn(List<String> productIds);

    void reduceStock(List<CartDTO> cartDTOList);
}
