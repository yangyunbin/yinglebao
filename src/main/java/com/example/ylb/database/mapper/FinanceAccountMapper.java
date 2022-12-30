package com.example.ylb.database.mapper;


import com.example.ylb.database.domain.FinanceAccount;

/**
* @author 19202
* @description 针对表【u_finance_account(用户财务资金账户表)】的数据库操作Mapper
* @createDate 2022-12-12 15:21:12
* @Entity generator.domain.FinanceAccount
*/
public interface FinanceAccountMapper {


    void add(FinanceAccount financeAccount);
}
