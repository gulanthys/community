package org.gulanthys.system.service.impl;

import org.community.common.Constants;
import org.community.common.Result;
import org.gulanthys.system.entity.LoginUser;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.service.LoginService;
import org.gulanthys.system.utils.JwtUtil;
import org.gulanthys.system.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户名登录
     *
     * @param user 用户信息
     * @return Result
     */
    public Result<?> Login(User user) {
        //AuthenticationManager authentication进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证失败
        if (Objects.isNull(authenticate)) {
            return Result.buildResult(Constants.Status.PWD_ERROR);
        }
        //认证通过生成jwtToken
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String uid = loginUser.getUser().getUid().toString();
        String jwtToken = JwtUtil.createJWT(uid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        //将完整的用户信息存入redis
        redisUtil.set("login:" + uid, loginUser);
        return Result.buildResult(Constants.Status.OK, "登录成功", map);
    }

    public Result<?> Logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer uid = loginUser.getUser().getUid();
        //删除redis中的值
        redisUtil.del("login:" + uid);
        return Result.buildResult(Constants.Status.OK, "注销成功");
    }
}
