package com.example.ylb.front.service;

import com.example.ylb.common.util.PageList;
import com.example.ylb.database.domain.ProductInfo;

import java.util.Map;

public interface IProductService {

    /**
     * 根据产品类型获取产品列表
     * @return
     */
    Map<String, Object> queryProductsByType();

    /**
     * 根据产品类型获取产品列表,分页
     * @param maps
     * @return
     */
    PageList<ProductInfo> queryProductListByType(Map<String, Integer> maps);


    /**
     * 根据产品ID查询产品的信息
     * @param pId
     * @return
     */
    ProductInfo queryProductById(Integer pId);
}
