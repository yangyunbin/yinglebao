package com.example.ylb.front.service.impl;

import com.example.ylb.common.constants.AppConstants;
import com.example.ylb.common.exception.BusinessException;
import com.example.ylb.common.util.MD5Utils;
import com.example.ylb.common.util.StrUtils;
import com.example.ylb.database.domain.FinanceAccount;
import com.example.ylb.database.domain.User;
import com.example.ylb.database.mapper.FinanceAccountMapper;
import com.example.ylb.database.mapper.UserMapper;
import com.example.ylb.front.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class IUserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public void phoneRegister(Map<String, String> maps) {

        String phone = maps.get("phone");
        String password = maps.get("password");
        String verifyCode = maps.get("verifyCode");

        //1.校验
        //1.手机号不能为空  //4.密码是否为空  //验证码是否为空
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(verifyCode)) {
            throw new BusinessException("参数不能为空");
        }

        //2.手机格式校验
        if (!Pattern.matches("0?(13|14|15|17|18)[0-9]{9}", phone)) {
            throw new BusinessException("手机号格式不正确");
        }

        //3.手机号是否注册
        User user = userMapper.queryByPhone(phone);
        if (user != null) {
            //手机号已经注册
            throw new BusinessException("改手机号已注册");
        }


        //5.密码格式是否正确
        if (!Pattern.matches("^[0-9a-zA-Z]+$", password)) {
            throw new BusinessException("密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)");
        }
        //6.验证码是否过期
        Object obj = redisTemplate.opsForValue().get(AppConstants.REGISTER + phone);
        if (obj == null) {
            throw new BusinessException("验证码已过期，请重新获取");
        }

        //7.验证码是否正确
        String code = obj.toString().split(":")[0];
        if (!verifyCode.equals(code)) {
            throw new BusinessException("验证码输入错误，请重新输入");
        }
        //md5对密码加密  加盐加密

        User user1 = createUser(phone, password);

        userMapper.add(user1);

        FinanceAccount financeAccount = createFinanceAccount(user1);
        financeAccountMapper.add(financeAccount);


    }

    @Override
    public Map<String, Object> loginPhone(Map<String, String> maps) {
        String phone = maps.get("phone");
        String password = maps.get("password");
        String verifyCode = maps.get("verifyCode");


        //1.手机号不能为空  //4.密码是否为空  //验证码是否为空
        if (StringUtils.isEmpty(phone)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(verifyCode)) {
            throw new BusinessException("参数不能为空");
        }
        //2.手机格式校验
        if (!Pattern.matches("0?(13|14|15|17|18)[0-9]{9}", phone)) {
            throw new BusinessException("手机号格式不正确");
        }

        //3.验证码是否过期
        Object obj = redisTemplate.opsForValue().get(AppConstants.LOGIN + phone);
        if (obj == null) {
            throw new BusinessException("验证码已过期，请重新获取");
        }

        //4.验证码是否正确
        String code = obj.toString().split(":")[0];
        if (!verifyCode.equals(code)) {
            throw new BusinessException("验证码输入错误，请重新输入");
        }

        //1.查询用户是否存在
        User dbUser = userMapper.queryByPhone(phone);
        if (dbUser == null) {
            throw new BusinessException("用户不存在，重新输入");
        }
        //2.手机号查询到用户再判断密码是否正确。
        String salt = dbUser.getSalt();
        String md5Password = MD5Utils.encrypByMd5(salt + password);
        if (!md5Password.equals(dbUser.getLoginPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        //校验通过了，登录成功
        //更新登录时间
        dbUser.setLastLoginTime(new Date());
        userMapper.updateLastLoginTime(dbUser);

        String token = StrUtils.getComplexRandomString(32);
        dbUser.setSalt("");
        dbUser.setLoginPassword("");

        //把user存入redis中
        redisTemplate.opsForValue().set(
                token, dbUser, 30, TimeUnit.MINUTES
        );

        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("userInfo",dbUser);

        return map;
    }

    private FinanceAccount createFinanceAccount(User user1) {
        FinanceAccount financeAccount = new FinanceAccount();
        financeAccount.setAvailableMoney(new BigDecimal(0));
        financeAccount.setUid(user1.getId());

        return financeAccount;
    }

    private User createUser(String phone, String password) {
        User user1 = new User();
        user1.setPhone(phone);
        //生成一个盐值
        String salt = StrUtils.getComplexRandomString(32);
        user1.setSalt(salt);
        //加盐加密
        String md5Password = MD5Utils.encrypByMd5(salt + password);
        user1.setLoginPassword(md5Password);
        user1.setAddTime(new Date());
        return user1;
    }
}
