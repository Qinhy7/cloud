package com.toni.order.client;

import com.toni.order.bean.ProductInfo;
import com.toni.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 17:08
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/getProductInfoByProductId")
    public List<ProductInfo> getProductInfoByProductId(List<String> productIdList);

    @PostMapping("/product/reduceStock")
    public void reduceStock(List<CartDTO> cartDTOList);

}
