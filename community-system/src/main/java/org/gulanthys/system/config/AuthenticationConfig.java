package org.gulanthys.system.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.gulanthys.system.entity.LoginUser;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.MenuMapper;
import org.gulanthys.system.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Configuration
public class AuthenticationConfig {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Bean
    public UserDetailsService usernameService() {
        return username -> {
            //查询用户信息
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserName, username);
            User user = userMapper.selectOne(queryWrapper);
            //如果不存在，抛出异常
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException("用户名错误");
            }
            //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
            List<String> list = menuMapper.selectPermsByUserId(user.getUid());
            //存在，将信息封装进UserDetails种
            return new LoginUser(user, list);
        };
    }

    @Bean
    public AuthenticationProvider usernameProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usernameService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    private PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
