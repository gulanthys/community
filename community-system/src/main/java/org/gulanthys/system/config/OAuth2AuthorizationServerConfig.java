package org.gulanthys.system.config;

import jakarta.annotation.Resource;
import org.gulanthys.system.service.LoginUserService;
import org.gulanthys.system.service.impl.JwtTokenEnhancer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private LoginUserService loginUserService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private TokenStore jwtTokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * @description: 使用密码模式所需配置
     * @author liyonghui
     * @date 2021/12/5 14:27
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        //配置JWT内容增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager).userDetailsService(loginUserService)
                //配置存储令牌策略
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //配置client-id
                .withClient("super")
                //配置client-secret
                .secret(passwordEncoder.encode("xxx"))

                //配置访问token的有效期
                .accessTokenValiditySeconds(3600)
                //配置redirect-url,用于授权成功后跳转
                //(实际这里写要访问的资源服务器的地址：例如：http://localhost:8080/login)
                .redirectUris("http://www.baidu.com")
                //自动授权
                .autoApprove(true)
                //配置申请的权限范围
                .scopes("user", "order", "payment")
                //授权类型-使用密码模式（添加了刷新令牌 refresh_token）
                .authorizedGrantTypes("password", "refresh_token", "authorization_code");
    }
}
