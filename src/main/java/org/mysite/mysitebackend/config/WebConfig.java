package org.mysite.mysitebackend.config;

import org.mysite.mysitebackend.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/user/captcha","/user/verify","/user/getUserInfoByName","/category/default");

        //WebMvcConfigurer.super.addInterceptors(registry);
    }
}
