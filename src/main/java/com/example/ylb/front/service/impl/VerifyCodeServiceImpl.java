package com.example.ylb.front.service.impl;

import com.example.ylb.common.constants.AppConstants;
import com.example.ylb.common.exception.BusinessException;
import com.example.ylb.common.util.SendMsgUtils;
import com.example.ylb.common.util.StrUtils;
import com.example.ylb.database.domain.User;
import com.example.ylb.database.mapper.UserMapper;
import com.example.ylb.front.service.IVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sendVerifyCode(Map<String, String> maps) {
        String phone = maps.get("phone");
        String type = maps.get("type");

        //校验
        //1.手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            throw new BusinessException("手机号不能为空");
        }

        //2.手机格式校验
        if (!Pattern.matches("0?(13|14|15|17|18)[0-9]{9}", phone)) {
            throw new BusinessException("手机号格式不正确");
        }

        if (type.equals("register")) {
            //3.手机号是否注册
            User user = userMapper.queryByPhone(phone);
            if (user != null) {
                //手机号已经注册
                throw new BusinessException("改手机号已注册");
            }
            Object rediscode = redisTemplate.opsForValue().get(AppConstants.REGISTER + phone);

            String code = "";
            if (rediscode == null) {
                //生成一个四位的验证码
                code = StrUtils.getRandomString(4);

                //存入redis中，并且发送给用户
            } else {
                String time = rediscode.toString().split(":")[1];
                if (System.currentTimeMillis() - Long.valueOf(time) < 1 * 60 * 1000) {

                    throw new RuntimeException("一分钟之内不允许重新获取验证码");
                } else {

                    code = rediscode.toString().split(":")[0];

                }
            }


            redisTemplate.opsForValue().set(
                    AppConstants.REGISTER + phone,   //key:业务键+phone
                    code + ":" + System.currentTimeMillis(),    //value:code:时间戳
                    3,                    //过期时间
                    TimeUnit.MINUTES
            );

            //发送给用户验证码
            //SendMsgUtils.sendCode(phone,"验证码" + "请在三分钟内使用");
            System.out.println("验证码" + code + "请在三分钟内使用");

        } else {
            Object rediscode = redisTemplate.opsForValue().get(AppConstants.LOGIN + phone);

            String code = "";
            if (rediscode == null) {
                //生成一个四位的验证码
                code = StrUtils.getRandomString(4);

                //存入redis中，并且发送给用户
            } else {
                String time = rediscode.toString().split(":")[1];
                if (System.currentTimeMillis() - Long.valueOf(time) < 1 * 60 * 1000) {

                    throw new RuntimeException("一分钟之内不允许重新获取验证码");
                } else {

                    code = rediscode.toString().split(":")[0];

                }
            }


            redisTemplate.opsForValue().set(
                    AppConstants.REGISTER + phone,   //key:业务键+phone
                    code + ":" + System.currentTimeMillis(),    //value:code:时间戳
                    3,                    //过期时间
                    TimeUnit.MINUTES
            );

            //发送给用户验证码
            //SendMsgUtils.sendCode(phone,"验证码" + "请在三分钟内使用");
            System.out.println("验证码" + code + "请在三分钟内使用");
        }
    }

}
