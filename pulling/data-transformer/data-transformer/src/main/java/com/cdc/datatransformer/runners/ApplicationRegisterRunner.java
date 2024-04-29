package com.cdc.datatransformer.runners;

import com.cdc.datatransformer.caches.Cache;
import com.cdc.datatransformer.caches.CacheRetriever;
import com.cdc.datatransformer.commons.DataTransformerCommons;
import com.cdc.datatransformer.models.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class ApplicationRegisterRunner implements ApplicationRunner {

    @Value("${cdc.endpoint.id-retriever.uri}")
    private String url;

    private final CacheRetriever cacheRetriever;
    private final WebClient webClient;

    public ApplicationRegisterRunner(CacheRetriever cacheRetriever, WebClient webClient) {
        this.cacheRetriever = cacheRetriever;
        this.webClient = webClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        retrieveAndCacheApplicationId();
    }

    private void retrieveAndCacheApplicationId() {
        registerApplication().subscribe(response -> {
            log.debug("ApplicationIdRunner :: registerApplication :: response :: {}", response);
            if (response.isSuccess()) {
                String applicationId = response.getData().get("id");
                log.info("ApplicationIdRunner :: registerApplication :: application got registered with Id : {}", applicationId);
                cacheRetriever.put(DataTransformerCommons.APPLICATION_ID_KEY, new Cache<>(applicationId));
            }
        });
    }

    private Mono<ApiResponse> registerApplication() {
        log.debug("ApplicationIdRunner :: registerApplication :: registering application using URL : {}", url);
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
}
