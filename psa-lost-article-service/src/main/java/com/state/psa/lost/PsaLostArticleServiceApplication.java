package com.state.psa.lost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class PsaLostArticleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsaLostArticleServiceApplication.class, args);
	}

}
