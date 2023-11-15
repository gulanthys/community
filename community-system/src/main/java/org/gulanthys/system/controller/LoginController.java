package org.gulanthys.system.controller;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Constants;
import org.community.common.Result;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.service.LoginService;
import org.gulanthys.system.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/log")
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("/login")
    public Result<?> Login(@RequestBody User user) {
        log.info("登录");
        Map<?, ?> map = loginService.Login(user.getUserName(), user.getPassword());
        return Result.buildResult(Constants.Status.OK, "登陆成功", map);
    }

    /**
     * 注销
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<?> Logout() {
        log.info("注销");
        return loginService.Logout();
    }

    /**
     * 注册
     *
     * @param user 注册信息
     * @return
     */
    @PostMapping("/register")
    public Result<?> Register(@RequestBody User user) {
        log.info("注册");
        boolean res = userService.save(user);
        return Result.buildResult(Constants.Status.OK, "注册结果", res);
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser1(Authentication authentication, HttpServletRequest request) {
        String head = request.getHeader("Authorization");
        String token = head.replace("bearer", "");

        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

}
