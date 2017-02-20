package com.example.event.untimedConstantType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
class UCEventConfiguration {

    UCEventFacade untimedConstantEventFacade() {
        InMemoryUCEventRepository repository = new InMemoryUCEventRepository();
        return untimedConstantEventFacade(repository);
    }

    @Bean
    UCEventFacade untimedConstantEventFacade(UCEventRepository eventRepository) {
        UCEventCreator eventCreator = new UCEventCreator();
        return new UCEventFacade(eventCreator, eventRepository);
    }

    static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
}
