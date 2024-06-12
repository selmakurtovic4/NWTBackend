package com.hoperise.hoperisegateway;

import com.hoperise.hoperisegateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class HoperiseGatewayApplication {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    public static void main(String[] args) {
        SpringApplication.run(HoperiseGatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("appointmentService", r -> r
                        .path("/appointment/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://appointment"))
                .route("medicalRecordService", p -> p
                        .path("/medical-record/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://medical-record"))
                .route("patientService", p -> p
                        .path("/patient/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://patient"))
                .route("securityService", p -> p
                        .path("/auth/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie"))
                        .uri("lb://security"))
                .build();
    }
}
