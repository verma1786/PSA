package com.state.psa.lost.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MessageConverter {

    @Bean
    public MappingJackson2HttpMessageConverter   mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new  MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }
}
