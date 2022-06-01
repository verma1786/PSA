package com.state.psa.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class RedisCacheMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheMasterApplication.class, args);
	}

}
