package com.example.hoperisegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class HoperiseGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoperiseGatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RouteLocator test(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("appointmentService", p -> p
                        .path("/appointment/**")
                        .uri("lb://appointment"))
                .route("medicalRecordService", p -> p
                        .path("/medical-record/**")
                        .uri("lb://medical-record"))
                .route("staffService", p -> p
                        .path("/Staff/**")
                        .uri("lb://Staff"))
                .build();
    }
}
