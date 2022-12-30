package com.example.ylb.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {

    //总条数
    private Integer totals = 0;
    //每页数据
    private List<T> rows = new ArrayList<>();

}

