package com.example.ylb.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppThreeCode {

    //历史年化收益率
    private BigDecimal avgRate;

    //累计用户
    private Integer countUser;

    //累计成交金额
    private BigDecimal sumBidMoney;
}
