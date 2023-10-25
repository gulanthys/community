package org.gulanthys.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {


//    @Bean
//    public UserDetailsService uidService() {
//        return uid -> {
//            //查询用户信息
//            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(User::getUid, uid);
//            User user = userMapper.selectOne(queryWrapper);
//            //如果不存在，抛出异常
//            if (Objects.isNull(user)) {
//                throw new UsernameNotFoundException("用户名错误");
//            }
//            //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
//            List<String> list = menuMapper.selectPermsByUserId(user.getUid());
//            //存在，将信息封装进UserDetails种
//            return new LoginUser(user, list);
//        };
//    }
//
//    @Bean
//    public AuthenticationProvider uidProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(uidService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}