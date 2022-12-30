package com.example.ylb.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BidRecode {

    private String phone;
    private String bidMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bidTime;

    public void setPhone(String phone) {
        this.phone = phone.substring(0, 3) + "****" + phone.substring(7);
    }
}
