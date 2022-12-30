package com.example.ylb.database.mapper;


import com.example.ylb.database.domain.User;

/**
* @author 19202
* @description 针对表【u_user(用户表)】的数据库操作Mapper
* @createDate 2022-12-12 15:21:12
* @Entity generator.domain.User
*/
public interface UserMapper {


    Integer queryCount();

    User queryByPhone(String phone);

    void add(User user1);

    void updateLastLoginTime(User dbUser);
}
