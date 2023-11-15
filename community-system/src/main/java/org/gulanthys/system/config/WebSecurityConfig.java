package org.gulanthys.system.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.gulanthys.system.entity.Menu;
import org.gulanthys.system.filter.JwtAuthenticationTokenFilter;
import org.gulanthys.system.mapper.MenuMapper;
import org.gulanthys.system.mapper.RoleMapper;
import org.gulanthys.system.service.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Slf4j
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private AntPathMatcher antPathMatcher;
    @Resource
    private LoginUserService loginUserService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //添加配置
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeHttpRequests((register) -> register
                        .requestMatchers("/log/login").anonymous()
                        .requestMatchers("/log/logout").authenticated()
                        .anyRequest().access(((authentication, object) -> {
                            //表示请求的 URL 地址和数据库的地址是否匹配上了
                            boolean isMatch = false;
                            //获取当前请求的 URL 地址
                            String requestURI = object.getRequest().getRequestURI();
                            log.info("RequestURI==>{}", requestURI);
                            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
                            queryWrapper.eq(Menu::getStatus, 0);
                            List<Menu> menus = menuMapper.selectList(queryWrapper);
                            for (Menu menu : menus) {
                                if (antPathMatcher.match(menu.getPath(), requestURI)) {
                                    isMatch = true;
                                    //说明找到了请求的地址了
                                    //这就是当前请求需要的角色
                                    List<String> roles = roleMapper.selectRoleByPath(menu.getPath());
                                    //获取当前登录用户的角色
                                    Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
                                    for (GrantedAuthority authority : authorities) {
                                        for (String role : roles) {
                                            if (authority.getAuthority().contains(role)) {
                                                //说明当前登录用户具备当前请求所需要的角色
                                                return new AuthorizationDecision(true);
                                            }
                                        }
                                    }
                                }
                            }
//                    if (!isMatch) {
//                        //说明请求的 URL 地址和数据库的地址没有匹配上，对于这种请求，统一只要登录就能访问
//                        if (authentication.get() instanceof AnonymousAuthenticationToken) {
//                            return new AuthorizationDecision(false);
//                        } else {
//                            //说明用户已经认证了
//                            return new AuthorizationDecision(true);
//                        }
//                    }
                            return new AuthorizationDecision(false);
                        })));
        //Oauth2 配置
        http.oauth2Login();
        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(loginUserService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}
