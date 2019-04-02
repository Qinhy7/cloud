package com.toni.product.controller;

import com.netflix.discovery.converters.Auto;
import com.toni.product.bean.ProductCategory;
import com.toni.product.bean.ProductInfo;
import com.toni.product.dto.CartDTO;
import com.toni.product.service.ProductCategoryService;
import com.toni.product.service.ProductInfoService;
import com.toni.product.util.ResultVOUtil;
import com.toni.product.vo.ProductCategoryVO;
import com.toni.product.vo.ProductInfoVO;
import com.toni.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 18:30
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询商品列表
     * @return
     */
    @RequestMapping("/list")
    public ResultVO list(){
        /**
         * 1. 查询所有的上架商品信息
         * 2. 获取1中对应的商品分类
         * 3. 根据2中商品分类查询其对应的详细信息
         * 4. 拼装返回结果
         */

        //1.
        List<ProductInfo> allUp = productInfoService.getAllUp();
        //2.
        List<Integer> allUpCategoryTypes = allUp.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        //3.
        List<ProductCategory> productCategoryList = productCategoryService.getAllByCategoryIn(allUpCategoryTypes);
        //4.
        ArrayList<ProductCategoryVO> productCategoryVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory, productCategoryVO);

            ArrayList<ProductInfoVO> productInfoVOS = new ArrayList<>();

            for (ProductInfo productInfo : allUp) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }

            productCategoryVO.setProductInfoVOList(productInfoVOS);

            productCategoryVOS.add(productCategoryVO);
        }

        //5. 返回結果
        return ResultVOUtil.success(productCategoryVOS);
    }


    /**
     * 获取指定商品id的详细信息
     * @param productIdList
     * @return
     */
    @PostMapping("/getProductInfoByProductId")
    public List<ProductInfo> getProductInfoByProductId(List<String> productIdList){
        return productInfoService.getAllByProductIdIn(productIdList);
    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @PostMapping("/reduceStock")
    public void reduceStock(List<CartDTO> cartDTOList){
        productInfoService.reduceStock(cartDTOList);
    }

}
