package com.hk.server.config;

import com.hk.server.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    //构造方法
    public WebConfiguration(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/admin/login");     //管理员登录
        excludePath.add("/doctor/login");     //医生登录
        excludePath.add("/admin/article/**");     //
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/admin/**","/doctor/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
