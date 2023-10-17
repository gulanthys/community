package org.gulanthys.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.community.common.Result;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/log")
public class LoginController {
    /**
     * 登录
     */
    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("/login")
    public Result<?> Login(@RequestBody User user) {
        log.info("登录");
        return loginService.Login(user);
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("/logout")
    public Result<?> Logout() {
        log.info("登出");
        return loginService.Logout();
    }
}
