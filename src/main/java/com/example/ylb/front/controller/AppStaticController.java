package com.example.ylb.front.controller;



import com.example.ylb.common.result.JsonResult;
import com.example.ylb.common.vo.AppThreeCode;
import com.example.ylb.front.service.IAppStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ylb")
@CrossOrigin
public class AppStaticController {

    @Autowired
    private IAppStaticService appStaticService;

    @GetMapping("/appThreeCode")
    public JsonResult getAppThreeCode() {
        System.out.println("qingqiu");
        try {
           AppThreeCode atc  = appStaticService.getAppThreeCode();
           return JsonResult.me().setData(atc);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.me().setMsg("net error,try it later");
        }

    }
}
