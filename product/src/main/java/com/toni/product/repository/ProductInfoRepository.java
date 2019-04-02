package com.toni.product.repository;

import com.toni.product.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:37
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> getAllByProductStatus(Integer productStatu);


    List<ProductInfo> getAllByProductIdIn(List<String> productIds);

}
