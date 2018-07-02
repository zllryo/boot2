package com.ryo.boot2.model.common;

import com.ryo.boot2.controller.UserController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("webapi")
public class JerseyConfig extends ResourceConfig {

    public  JerseyConfig()
    {
        //注册类的方式
        register(UserController.class);
        register(WadlResource.class);
        //注册包的方式
        //packages("com.ryo.boot2.controller");

    }

    @PostConstruct
    public void init() {
        // Register components where DI is needed
        this.configureSwagger();
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-docker-example");
        config.setTitle("Spring Boot + Jersey + Swagger + Docker Example");
        config.setVersion("v1");
        config.setContact("wzh");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath("webapi");
        config.setResourcePackage("com.ryo.boot2");
        config.setPrettyPrint(true);
        //config.setScan(true);
    }

}
