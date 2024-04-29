package com.cdc.data.change.services.impl;

import com.cdc.commons.constants.CdcConstants;
import com.cdc.data.change.services.WikimediaService;
import com.cdc.data.model.dtos.WikimediaEventDTO;
import com.cdc.data.model.entities.WikimediaEvent;
import com.cdc.data.model.mapper.WikimediaEventMapper;
import com.cdc.data.model.repositories.WikimediaEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Slf4j
public class WikimediaServiceImpl implements WikimediaService {

    private final WebClient webClient;
    private final WikimediaEventMapper mapper;
    private final WikimediaEventRepository wikimediaEventRepository;
    private Disposable subscription;

    public WikimediaServiceImpl(WikimediaEventMapper wikimediaEventMapper, WikimediaEventRepository wikimediaEventRepository) {
        this.wikimediaEventRepository = wikimediaEventRepository;
        this.webClient = WebClient.create(CdcConstants.WIKIMEDIA_URL);
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
        if (Objects.nonNull(subscription) && !subscription.isDisposed()) {
            log.info("Going to dispose the subscription");
            subscription.dispose();
        }
    }


}
