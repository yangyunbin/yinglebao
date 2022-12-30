package com.example.ylb.front.service.impl;

import com.example.ylb.common.constants.AppConstants;
import com.example.ylb.common.util.PageList;
import com.example.ylb.database.domain.ProductInfo;
import com.example.ylb.database.mapper.ProductInfoMapper;
import com.example.ylb.front.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements IProductService {


    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public Map<String, Object> queryProductsByType() {
        //新手宝
        List<ProductInfo> xinList =  productInfoMapper.queryProductsByType(AppConstants.PRODUCT_TYPE_XIN,1);
        //优选
        List<ProductInfo> youList =  productInfoMapper.queryProductsByType(AppConstants.PRODUCT_TYPE_YOU,3);
        //散标
        List<ProductInfo> sanList =  productInfoMapper.queryProductsByType(AppConstants.PRODUCT_TYPE_SAN,3);

        HashMap<String, Object> map = new HashMap<>();
        map.put("xinList",xinList);
        map.put("youList",youList);
        map.put("sanList",sanList);
        return map;
    }

    @Override
    public PageList<ProductInfo> queryProductListByType(Map<String, Integer> maps) {
        Integer pType = maps.get("productType");//产品类型
        Integer localPage = maps.get("localPage");
        Integer pageSize = maps.get("pageSize");
        Integer start = (localPage - 1) * pageSize;
         Integer totals = productInfoMapper.queryCount(pType);
         List<ProductInfo> rows = productInfoMapper.queryPage(pType,start,pageSize);
        return new PageList<>(totals,rows);
    }

    @Override
    public ProductInfo queryProductById(Integer pId) {
        return productInfoMapper.queryProductById(pId);
    }
}
