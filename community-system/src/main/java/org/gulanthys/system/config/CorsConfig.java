package org.gulanthys.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器——跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//添加请求映射
                .allowedOrigins("*")//特定来源的允许列表
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")//请求方式
                .allowCredentials(true)//是否携带cookie
                .maxAge(3600)//缓存时间
                .allowedHeaders("*");//请求头
    }
}
