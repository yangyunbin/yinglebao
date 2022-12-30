package com.example.ylb.front.controller;


import com.example.ylb.common.result.JsonResult;
import com.example.ylb.common.vo.BidRecode;
import com.example.ylb.common.vo.InvestmentRanking;
import com.example.ylb.front.service.IBidInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bidInfo")
@Api(tags = {"平台投标记录接口"})
@CrossOrigin
public class BidInfoController {

    @Autowired
    private IBidInfoService bidInfoService;

    @ApiOperation(value = "获取投资排行榜信息")
    @GetMapping("/investmentRanking")
    public JsonResult getInvestmentRanking() {
        try {
            List<InvestmentRanking> rankList = bidInfoService.investmentRanking();
            return JsonResult.me().setData(rankList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }


    @ApiOperation(value = "查询投资记录")
    @GetMapping("/bidRecord")
    public JsonResult queryBidRecord() {
        try {
            List<BidRecode> bidRecodeList = bidInfoService.queryBidRecord();
            //List<BidRecord> bidRecodeList = bidInfoService.queryBidRecord();
            return JsonResult.me().setData(bidRecodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }
}
