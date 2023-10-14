package org.gulanthys.user.filter;


import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.gulanthys.user.entity.LoginUser;
import org.gulanthys.user.utils.JwtUtil;
import org.gulanthys.user.utils.RedisUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = (LoginUser) redisUtil.get(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到 Authentication 中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
