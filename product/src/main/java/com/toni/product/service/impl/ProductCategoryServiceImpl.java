package com.toni.product.service.impl;

import com.toni.product.bean.ProductCategory;
import com.toni.product.repository.ProductCategoryRepository;
import com.toni.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:54
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;


    @Override
    public List<ProductCategory> getAllByCategoryIn(List<Integer> categorys) {
        return repository.findAllByCategoryTypeIn(categorys);
    }
}
