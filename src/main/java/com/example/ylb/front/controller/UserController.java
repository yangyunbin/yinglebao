package com.example.ylb.front.controller;


import com.example.ylb.common.exception.BusinessException;
import com.example.ylb.common.result.JsonResult;
import com.example.ylb.common.util.PageList;
import com.example.ylb.database.domain.ProductInfo;
import com.example.ylb.front.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "平台用户接口")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取产品列表信息",notes = "根据产品类型获取")
    @PostMapping("/phoneRegister")
    public JsonResult phoneRegister(@RequestBody Map<String,String> maps){
        try {
             userService.phoneRegister(maps);
            return JsonResult.me();
        }catch (BusinessException e){
            e.printStackTrace();
            return JsonResult.me().setMsg(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }


    @ApiOperation(value = "手机号登录接口")
    @PostMapping("/loginPhone")
    public JsonResult loginPhone(@RequestBody Map<String,String> maps){
        try {
            //map包含token和userInfo
            Map<String,Object> map = userService.loginPhone(maps);
             return JsonResult.me().setData(map);
        }catch (BusinessException e){
            e.printStackTrace();
            return JsonResult.me().setMsg(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error");
        }
    }

}
