package com.example.ylb.front.service.impl;

import com.example.ylb.common.vo.AppThreeCode;
import com.example.ylb.database.mapper.BidInfoMapper;
import com.example.ylb.database.mapper.ProductInfoMapper;
import com.example.ylb.database.mapper.UserMapper;
import com.example.ylb.front.service.IAppStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AppStaticServiceImpl implements IAppStaticService {

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public AppThreeCode getAppThreeCode() {

        //历史年化收益率
        BigDecimal avgRate = productInfoMapper.queryAvgRate();

        //平台用户数
        Integer count = userMapper.queryCount();

        //累计成交金额
        BigDecimal money = bidInfoMapper.sumBidMoney();

        return new AppThreeCode(avgRate, count, money);

    }
}
