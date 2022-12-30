package com.example.ylb.database.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 投资记录表
 * @TableName b_bid_info
 */
@Data
public class BidInfo implements Serializable {
    /**
     * 投标记录ID
     */
    private Integer id;

    /**
     * 产品ID
     */
    private Integer prodId;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 投标金额
     */
    private BigDecimal bidMoney;

    /**
     * 投标时间
     */
    private Date bidTime;

    /**
     * 投标状态
     */
    private Integer bidStatus;

    private static final long serialVersionUID = 1L;
}