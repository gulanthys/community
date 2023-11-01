package org.gulanthys.system.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.gulanthys.system.entity.Menu;
import org.gulanthys.system.filter.JwtAuthenticationTokenFilter;
import org.gulanthys.system.mapper.MenuMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private AntPathMatcher antPathMatcher;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //添加配置
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(register -> register.requestMatchers("/log/login").anonymous().anyRequest().access(((authentication, object) -> {
                    //表示请求的 URL 地址和数据库的地址是否匹配上了
                    boolean isMatch = false;
                    //获取当前请求的 URL 地址
                    String requestURI = object.getRequest().getRequestURI();
                    LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Menu::getStatus, 0);
                    List<Menu> menuList = menuMapper.selectList(queryWrapper);
                    for (Menu menu : menuList) {
                        if (antPathMatcher.match(menu.getPath(), requestURI)) {
                            isMatch = true;
                            //说明找到了请求的地址了
                            //这就是当前请求需要的角色
                            String perms = menu.getPerms();
                            //获取当前登录用户的角色
                            Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
                            for (GrantedAuthority authority : authorities) {
                                if (authority.getAuthority().contains(perms)) {
                                    //说明当前登录用户具备当前请求所需要的角色
                                    return new AuthorizationDecision(true);
                                }
                            }
                        }
                    }
                    if (!isMatch) {
                        //说明请求的 URL 地址和数据库的地址没有匹配上，对于这种请求，统一只要登录就能访问
                        if (authentication.get() instanceof AnonymousAuthenticationToken) {
                            return new AuthorizationDecision(false);
                        } else {
                            //说明用户已经认证了
                            return new AuthorizationDecision(true);
                        }
                    }
                    return new AuthorizationDecision(false);
                })));

        http.logout(logout -> logout.logoutUrl("/log/logout").permitAll());

        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
