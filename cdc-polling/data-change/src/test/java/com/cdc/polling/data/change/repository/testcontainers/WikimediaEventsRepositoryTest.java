package com.cdc.polling.data.change.repository.testcontainers;

import com.cdc.polling.data.model.entities.WikimediaEvent;
import com.cdc.polling.data.model.repositories.WikimediaEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// do not replace the testcontainer data source
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
//@Profile("integration")
public class WikimediaEventsRepositoryTest {

    @Autowired
    private WikimediaEventRepository wikimediaEventRepository;

    @Autowired
    private Environment environment;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @Test
    public void testSaveWikimedia() {
        System.out.println("Active Spring profile(s): " + String.join(", ", environment.getActiveProfiles()));

        WikimediaEvent wikimediaEvent = createMockWikimediaEvent();
        wikimediaEventRepository.save(wikimediaEvent);

        List<WikimediaEvent> wikimediaEvents = wikimediaEventRepository.findAll();

        assertNotNull(wikimediaEvents);
        assertEquals(1, wikimediaEvents.size());
        assertEquals(wikimediaEvent, wikimediaEvents.getFirst());

        Optional<WikimediaEvent> savedWikimediaEvent = wikimediaEventRepository.findById(wikimediaEvent.getId());
        assertTrue(savedWikimediaEvent.isPresent());
        assertEquals(wikimediaEvent, savedWikimediaEvent.get());
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
