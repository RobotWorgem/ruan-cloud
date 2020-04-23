package com.ruan.polestar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableEurekaClient
public class PolestarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolestarApplication.class, args);
    }

}
