package com.toni.product.repository;

import com.toni.product.bean.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:51
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> productCategories);
}
