package com.ryo.boot2.model.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi()
    {
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("girlsAPI接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ryo.boot2.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("test系统")
                .description("girls服务")
                .termsOfServiceUrl("http://blog.52itstyle.com")
                .contact(new Contact("socool网 ", "http://blog.52itstyle.com", "345849402@qq.com"))
                .version("1.0").build();
    }

}
