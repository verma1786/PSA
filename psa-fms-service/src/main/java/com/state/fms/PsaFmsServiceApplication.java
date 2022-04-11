package com.state.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PsaFmsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsaFmsServiceApplication.class, args);
	}

}
