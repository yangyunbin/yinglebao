package com.example.ylb.front.service.impl;


import com.example.ylb.common.vo.BidRecode;
import com.example.ylb.common.vo.InvestmentRanking;
import com.example.ylb.database.mapper.BidInfoMapper;
import com.example.ylb.front.service.BidInfoService;
import com.example.ylb.front.service.IBidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 19202
 * @description 针对表【b_bid_info(投资记录表)】的数据库操作Service实现
 * @createDate 2022-12-12 15:21:12
 */
@Service
public class BidInfoServiceImpl implements IBidInfoService {

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Override
    public List<InvestmentRanking> investmentRanking() {

        return bidInfoMapper.investmentRanking();
    }

    @Override
    public List<BidRecode> queryBidRecord() {
        return bidInfoMapper.queryBidRecord();
    }
}
