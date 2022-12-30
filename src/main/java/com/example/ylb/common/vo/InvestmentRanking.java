package com.example.ylb.common.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvestmentRanking {


    //总投资额
    private BigDecimal sumBidMoney;
    //投资人电话
    private String uphone;

    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone.substring(0,3) + "****" + uphone.substring(7);
    }
}
