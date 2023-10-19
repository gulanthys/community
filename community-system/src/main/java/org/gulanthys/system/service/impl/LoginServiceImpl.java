package org.gulanthys.system.service.impl;

import jakarta.annotation.Resource;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    //存储位置
    private final String address = "user:login:";
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public Result<?> Login(User user) {
        //存储认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //对认证信息进行认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果失败则抛出异常
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("认证失败");
        }

        //成功则存入LoginUser
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获取当前用户的id
        String uid = loginUser.getUser().getUid().toString();

        //通过Jwt创建token并存入map
        Long time = 24 * 60 * 60 * 1000L;// 60 * 60 *1000  一个小时
        String token = JwtUtil.createJWT(uid, time);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        //将用户信息存入redis
        redisUtil.set(address + uid, loginUser);

        return Result.buildResult(Constants.Status.OK, "登录成功", map);
    }

    @Override
    public Result<?> Logout() {
        //从SecurityContextHolder种获取认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //提取登录信息
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        //获取uid
        String uid = loginUser.getUser().getUid().toString();
        //从redis种删除token
        redisUtil.del(address + uid);
        return Result.buildResult(Constants.Status.OK, "注销成功");
    }
}
