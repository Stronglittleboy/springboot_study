package com.hlw.springboot_study.controller;

import com.hlw.springboot_study.mapper.UserMapper;
import com.hlw.springboot_study.service.bo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String hello(){
        return "Hello,World!";
    }
    @GetMapping("/user")
    public UserInfo getUserInfo(@RequestParam(value = "name",defaultValue = "杜甫") String name){
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName(name);
//        userInfo.setAge(20);
//        userInfo.setDesc("奇怪的一个男人");
        UserInfo userInfo = userMapper.queryUserList().get(0);
        return userInfo;
    }
}
