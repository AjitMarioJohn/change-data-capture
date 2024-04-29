package com.cdc.datatransformer.runners;

import com.cdc.datatransformer.caches.Cache;
import com.cdc.datatransformer.caches.CacheRetriever;
import com.cdc.datatransformer.commons.DataTransformerCommons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ApplicationDeRegisterListener implements ApplicationListener<ApplicationEvent> {

    @Value("${cdc.endpoint.id-retriever.uri}")
    private String url;

    private final CacheRetriever cacheRetriever;
    private final WebClient webClient;

    public ApplicationDeRegisterListener(CacheRetriever cacheRetriever, WebClient webClient) {
        this.cacheRetriever = cacheRetriever;
        this.webClient = webClient;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextClosedEvent) {
            log.info("ApplicationDeRegisterListener :: onApplicationEvent :: Application is shutting down.");
            deRegisterApplication();
            log.info("ApplicationDeRegisterListener :: onApplicationEvent :: application got registered successfully!!");
        }
    }

    private void deRegisterApplication() {
        log.debug("ApplicationDeRegisterListener :: onApplicationEvent :: de-registering application using URL : {}", url);
        final Cache<String> applicationId = cacheRetriever.get(DataTransformerCommons.APPLICATION_ID_KEY);
        Map<String, String> body = Map.of("instanceId", applicationId.instance());
        this.webClient.put()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientRequestException.class, e -> {
                    // handle error here, e.g. log a message or perform cleanup
                    log.error("Error deregistering application: {} ", e.getMessage(), e );
                    return Mono.empty();
                })
                .doOnSuccess(result -> {
                    log.debug("ApplicationDeRegisterListener :: onApplicationEvent :: result : {}", result);
                    cacheRetriever.remove(DataTransformerCommons.APPLICATION_ID_KEY);
                })
                .block();
    }
}
