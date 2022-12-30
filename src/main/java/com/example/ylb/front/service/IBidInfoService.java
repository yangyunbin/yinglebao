package com.example.ylb.front.service;

import com.example.ylb.common.vo.BidRecode;
import com.example.ylb.common.vo.InvestmentRanking;

import java.util.List;

public interface IBidInfoService {
    List<InvestmentRanking> investmentRanking();

    List<BidRecode> queryBidRecord();
}
