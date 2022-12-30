package com.example.ylb.front.controller;


import com.example.ylb.common.exception.BusinessException;
import com.example.ylb.common.result.JsonResult;
import com.example.ylb.common.vo.AppThreeCode;
import com.example.ylb.front.service.IAppStaticService;
import com.example.ylb.front.service.IVerifyCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/verifyCode")
@Api(tags = {"平台验证码处理接口"})
@CrossOrigin
public class VerifyCodeController {

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @ApiOperation(value = "注册业务的验证码的实现")
    @PostMapping("/sendVerifyCode")
    public JsonResult sendVerifyCode(@RequestBody Map<String,String> maps) {
        try {
             verifyCodeService.sendVerifyCode(maps);
            return JsonResult.me();
        } catch (BusinessException e){//业务异常
            e.printStackTrace();
            return JsonResult.me().setMsg(e.getMessage());

        }
        catch (Exception e) {//系统异常
            e.printStackTrace();
            return JsonResult.me().setMsg("net error,try it later");
        }

    }
}
