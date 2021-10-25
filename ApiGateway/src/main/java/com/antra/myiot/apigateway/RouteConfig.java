package com.antra.myiot.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .uri("lb://IotUserService")
                        )

                .route(r -> r.path("/authenticate","/validat")
                        .uri("lb://AuthService"))
                .build();
    }

}