package com.atto.server.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dhjung on 2017. 9. 1..
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**") // /로 시작하는 url은 모두 intercepter를 거친다.
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/error");
    }
}