package com.example.ylb.database.mapper;


import com.example.ylb.common.vo.BidRecode;
import com.example.ylb.common.vo.InvestmentRanking;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 19202
* @description 针对表【b_bid_info(投资记录表)】的数据库操作Mapper
* @createDate 2022-12-12 15:21:12
* @Entity generator.domain.BidInfo
*/
public interface BidInfoMapper {


    /**
     *
     * @return
     */
    BigDecimal sumBidMoney();

    /**
     * 投资排行
     * @return
     */
    List<InvestmentRanking> investmentRanking();


    /**
     * 查询投资记录
     * @return
     */
    List<BidRecode> queryBidRecord();
}
