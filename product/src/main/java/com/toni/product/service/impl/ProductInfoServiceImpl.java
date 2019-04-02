package com.toni.product.service.impl;

import com.toni.product.bean.ProductInfo;
import com.toni.product.dto.CartDTO;
import com.toni.product.enums.ProductErrorEnums;
import com.toni.product.enums.ProductInfoEnum;
import com.toni.product.exception.ProductException;
import com.toni.product.repository.ProductInfoRepository;
import com.toni.product.service.ProductInfoService;
import com.toni.product.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:40
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> getAllUp() {
        return repository.getAllByProductStatus(ProductInfoEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> getAllByProductIdIn(List<String> productIds) {
        return repository.getAllByProductIdIn(productIds);
    }

    @Transactional
    @Override
    public void reduceStock(List<CartDTO> cartDTOList) {

        List<ProductInfo> productInfos = new ArrayList<>();

        // 遍历每个商品
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo one = repository.getOne(cartDTO.getProductId());
            if(null == one){
                throw new ProductException(ProductErrorEnums.PRODUCT_NOT_EXITS);
            }

            // 库存操作
            int i = one.getProductStock() - cartDTO.getProductQuantity();
            if(i < 0){
                throw new ProductException(ProductErrorEnums.PRODUCT_STOCK_EMPTY);
            }
            one.setProductStock(i);
            ProductInfo save = repository.save(one);

            productInfos.add(save);
        }

        // 在库存修改后，发送mq消息
        sendMessage(productInfos);

    }

    // 发送消息
    public void sendMessage(List<ProductInfo> productInfos) {
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfos));
    }
}
