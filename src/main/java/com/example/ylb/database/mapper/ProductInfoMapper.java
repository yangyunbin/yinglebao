package com.example.ylb.database.mapper;


import com.example.ylb.database.domain.ProductInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 19202
* @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
* @createDate 2022-12-12 15:21:12
* @Entity generator.domain.ProductInfo
*/
public interface ProductInfoMapper {


    BigDecimal queryAvgRate();

    List<ProductInfo> queryProductsByType(@Param("productType") Integer productType,@Param("pageSize")  Integer pageSize);


    /**
     * 有多个参数时
     * @param pType
     * @return
     */
    Integer queryCount(Integer pType);


    /**
     * 根据产品类型分页
     * @param start
     * @param pageSize
     * @return
     */
    List<ProductInfo> queryPage(@Param("pType") Integer pType,@Param("start")  Integer start, @Param("pageSize") Integer pageSize);

    /**
     * 根据产品ID查询产品信息
     * @param pId
     * @return
     */
    ProductInfo queryProductById(Integer pId);
}
