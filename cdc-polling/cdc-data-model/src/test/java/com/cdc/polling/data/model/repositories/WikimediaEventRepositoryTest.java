package com.cdc.polling.data.model.repositories;

import com.cdc.polling.data.model.entities.WikimediaEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {WikimediaEventRepository.class})
class WikimediaEventRepositoryTest {

    @Autowired
    private WikimediaEventRepository wikimediaEventRepository;

    @Test
    public void testSaveWikimedia() {
        WikimediaEvent wikimediaEvent = createMockWikimediaEvent();
        wikimediaEventRepository.save(wikimediaEvent);

        List<WikimediaEvent> wikimediaEvents = wikimediaEventRepository.findAll();

        assertNotNull(wikimediaEvents);
    }

    private WikimediaEvent createMockWikimediaEvent() {
        WikimediaEvent wikimediaEvent = new WikimediaEvent();
        wikimediaEvent.setEventTime("1708994107");
        wikimediaEvent.setUser("Emijrpbot");
        wikimediaEvent.setTitle("List of active sumo wrestlers");
        wikimediaEvent.setTitleUrl("https://commons.wikimedia.org/wiki/File:Pensar_em_voc%C3%AA_(5404458360).jpg");
        return wikimediaEvent;
    }
}