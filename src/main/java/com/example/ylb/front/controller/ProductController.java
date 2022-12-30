package com.example.ylb.front.controller;


import com.example.ylb.common.result.JsonResult;
import com.example.ylb.common.util.PageList;
import com.example.ylb.database.domain.ProductInfo;
import com.example.ylb.front.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/product")
@Api(tags = "平台首页产品列表接口")
@CrossOrigin
public class ProductController {


    @Autowired
    private IProductService productService;

    @ApiOperation(value = "获取产品列表信息",notes = "根据产品类型获取")
    @GetMapping("/index")
    public JsonResult getProductsByType(){
        try {
            Map<String, Object> map = productService.queryProductsByType();
            return JsonResult.me().setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }

    @ApiOperation(value = "根据产品类型获取产品列表信息，分页")
    @PostMapping("/queryProductListByType")
    public JsonResult queryProductListByType(@RequestBody Map<String,Integer> maps){
        try {
            PageList<ProductInfo> pageList = productService.queryProductListByType(maps);
            return JsonResult.me().setData(pageList);
        } catch (Exception e) {
           e.printStackTrace();
           return JsonResult.me().setMsg("net error").setData(new PageList<>());
        }
    }

    @ApiOperation(value = "根据产品id获取产品信息")
    @GetMapping("/queryById/{pId}")
    public JsonResult queryById(@PathVariable("pId") Integer pId){
        try {
             ProductInfo productInfo  =  productService.queryProductById(pId);
            return JsonResult.me().setData(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }
}
