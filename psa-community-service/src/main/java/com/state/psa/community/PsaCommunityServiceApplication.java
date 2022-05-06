package com.state.psa.community;

import com.state.psa.community.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@PropertySource("classpath:/messages/business/uri.properties")
public class PsaCommunityServiceApplication {
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	RestTemplate restTemplate;

	@Value("${get.states.url}")
	private String restUrl;


	public static void main(String[] args) {
		SpringApplication.run(PsaCommunityServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Cache cache = cacheManager.getCache("stateCache");
			ResponseEntity<List<State>> responseEntity = restTemplate.exchange(restUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<State>>() {});
			List<State> states = responseEntity.getBody();
			for(State state:states){
				cache.put(state.getStateId(), state.getState());
				System.out.println(state.getStateId()+" : "+ state.getState());
			}

			System.out.println(cache.get(5).get());
		};
	}
}
