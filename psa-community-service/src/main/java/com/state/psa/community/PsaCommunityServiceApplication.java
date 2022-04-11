package com.state.psa.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PsaCommunityServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PsaCommunityServiceApplication.class, args);
	}

//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
// }

}
