package org.gulanthys.system.handler;

import com.alibaba.fastjson.JSON;
import org.community.common.Result;
import org.gulanthys.system.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = new Result(HttpStatus.UNAUTHORIZED.toString(), "用户认证失败，请查询登录");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtil.renderString(response, json);
    }
}
