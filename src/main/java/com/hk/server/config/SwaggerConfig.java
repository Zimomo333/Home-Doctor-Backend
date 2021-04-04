package com.hk.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("北大天才班后端接口文档")
                .description("有bug就找惠城")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hk.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
