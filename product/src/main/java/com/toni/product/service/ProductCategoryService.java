package com.toni.product.service;

import com.toni.product.bean.ProductCategory;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:53
 */
public interface ProductCategoryService {

    List<ProductCategory> getAllByCategoryIn(List<Integer> categorys);

}
