package com.yanty.friends.controller;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.TypeReference;
import com.yanty.friends.FeignClient.UserFeignClient;
import com.yanty.friends.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/hello")
    public String hello(){
        log.info("请求到hello");
        return "a;ifushwqiu";
    }


    @GetMapping("/testLink")
    public String testLink(){
        String resultString = userFeignClient.testLink();
        Result res = JSONObject.parseObject(resultString, Result.class);
        List<User> list = JSONObject.parseObject(res.getData().toString(), new TypeReference<ArrayList<User>>() {});
        list.forEach(System.out::println);

        return "ok";
    }




}
