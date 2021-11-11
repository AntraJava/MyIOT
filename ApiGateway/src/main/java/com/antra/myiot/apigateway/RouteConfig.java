package com.antra.myiot.apigateway;

import com.antra.myiot.apigateway.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class RouteConfig {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.method(HttpMethod.POST).and().path("/customer").uri("lb://IotUserService"))
                .route(r -> r.method(HttpMethod.OPTIONS).and().path("/customer").uri("lb://IotUserService"))
                .route(r -> r.path("/customer/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://IotUserService"))
                .route(r -> r.path("/customer/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://IotUserService"))
                .route(r -> r.path("/home").and().method(HttpMethod.GET).filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://IotHomeService"))
                .route(r -> r.path("/home/**").and().method(HttpMethod.GET).uri("lb://IotHomeService"))
                .route(r -> r.path("/home/**").and().method(HttpMethod.POST).filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://IotHomeService"))
                .route(r -> r.path("/authenticate","/validate").uri("lb://AuthService"))
                .build();
    }
}
