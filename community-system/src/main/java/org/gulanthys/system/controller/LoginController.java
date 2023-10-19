package org.gulanthys.system.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Result;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param user 登录信息
     * @return
     */
    @PostMapping("/login")
    public Result<?> Login(@RequestBody User user) {
        log.info("User--------->{}", user);
        return loginService.Login(user);
    }

    @PostMapping("/logout")
    public Result<?> Logout() {
        return loginService.Logout();
    }
}
