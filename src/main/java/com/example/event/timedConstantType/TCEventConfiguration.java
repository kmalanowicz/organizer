package com.example.event.timedConstantType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
class TCEventConfiguration {

    TCEventFacade timedConstantEventFacade() {
        InMemoryTCEventRepository repository = new InMemoryTCEventRepository();
        return timedConstantEventFacade(repository);
    }

    @Bean
    TCEventFacade timedConstantEventFacade(TCEventRepository eventRepository) {
        TCEventCreator eventCreator = new TCEventCreator();
        return new TCEventFacade(eventCreator, eventRepository);
    }

    static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
}
