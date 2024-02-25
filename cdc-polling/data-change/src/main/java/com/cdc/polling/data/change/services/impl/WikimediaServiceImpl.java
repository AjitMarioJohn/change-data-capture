package com.cdc.polling.data.change.services.impl;

import com.cdc.polling.commons.constants.DataPollingConstants;
import com.cdc.polling.data.model.dtos.WikimediaEventDTO;
import com.cdc.polling.data.change.services.WikimediaService;
import com.cdc.polling.data.model.entities.WikimediaEvent;
import com.cdc.polling.data.model.mapper.WikimediaEventMapper;
import com.cdc.polling.data.model.repositories.WikimediaEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class WikimediaServiceImpl implements WikimediaService {

    private final WebClient webClient;
    private final WikimediaEventMapper mapper;
    private final WikimediaEventRepository wikimediaEventRepository;
    private Disposable subscription;

    public WikimediaServiceImpl(WikimediaEventMapper wikimediaEventMapper, WikimediaEventRepository wikimediaEventRepository) {
        this.wikimediaEventRepository = wikimediaEventRepository;
        this.webClient = WebClient.create(DataPollingConstants.WIKIMEDIA_URL);
        this.mapper = wikimediaEventMapper;
    }

    @Override
    public void startStreaming() {
        log.info("Started streaming");
        Flux<WikimediaEventDTO> dataFlux = webClient.get()
                .retrieve()
                .bodyToFlux(WikimediaEventDTO.class);

        subscription = dataFlux.subscribe(data -> {
            log.debug("dto :: {}",Objects.toString(data, null));
            WikimediaEvent  wikimediaEvent = mapper.dtoToEntity(data);
            log.debug("entity :: {}", wikimediaEvent);
            wikimediaEventRepository.save(wikimediaEvent);
        });

    }

    @Override
    public void stopStreaming() {
        if (!subscription.isDisposed()) {
            log.info("Subscription not disposed. Going to dispose");
            subscription.dispose();
        }
    }


}
