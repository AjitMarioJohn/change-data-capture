package com.cdc.datatransformer.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${cdc.endpoint.id-retriever.host}")
    private String host;
    @Bean
    public WebClient webClient() {
        return WebClient.create(host);
    }
}
