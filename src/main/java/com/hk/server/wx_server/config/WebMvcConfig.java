package com.hk.server.wx_server.config;

import com.hk.server.interceptor.TokenInterceptor;
import com.hk.server.wx_server.interceptor.WxTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private WxTokenInterceptor tokenInterceptor;

    //构造方法
    public WebMvcConfig(WxTokenInterceptor tokenInterceptor){
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
        excludePath.add("/wx_user/login");
        excludePath.add("/wx_user/find_doctor_by_department");
        excludePath.add("/wx_user/get_doctor_message");
        excludePath.add("/wx_user/get_comments");
        excludePath.add("/wx_user/find_goodCommonts_doctor");
        excludePath.add("/wx_user/find_services_doctor");
        excludePath.add("/wx_user/find_priceasc_doctor");
        excludePath.add("/wx_user/find_pricedesc_doctor");
        excludePath.add("/wx_user/find_place_doctor");
        excludePath.add("/wx_user/search_doctor");
        excludePath.add("/wx_user/getHealthyAbouts");
        excludePath.add("/wx_user/getHealthyAboutById");
        excludePath.add("/wx_user/getWatch");
        excludePath.add("/wx_user/incrWatch");
        excludePath.add("/wx_user/getLikes");
        excludePath.add("/wx_user/incrLikes");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/wx_user/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
