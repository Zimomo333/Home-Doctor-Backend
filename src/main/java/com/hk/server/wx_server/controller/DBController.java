package com.hk.server.wx_server.controller;

import com.hk.server.entity.Doctor;
import com.hk.server.wx_server.dao.UserMapper;
import com.hk.server.wx_server.service.RedisService;
import com.hk.server.wx_server.service.WxService;
import com.hk.server.wx_server.utils.ScriptUtils;
import com.hk.server.wx_server.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("db")
public class DBController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    WxService wxService;

    @Autowired
    RedisService redisService;


    @GetMapping("/test")
    public Object test(@RequestParam("id")Long id){

        wxService.publishMessage(id);
        return null;

    }

    @GetMapping("testRedis")
    public Object testRedis(@RequestParam("id")String id){

        redisService.deleteArticleView(id);
        redisService.deleteArticleLikes(id);
        return null;
    }

//    @GetMapping("/insertDoctor")
//    public int insertDoctor(){
//        int sum = 0;
//        for (int i = 1;i<=10;i++) {
//            Doctor doctor = ScriptUtils.produceDoctorMessage();
//            int result = userMapper.insertDoctorMessage(doctor);
//            sum+=result;
//        }
//        return sum;
//    }

}
