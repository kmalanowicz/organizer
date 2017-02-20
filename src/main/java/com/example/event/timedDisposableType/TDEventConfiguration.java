package com.example.event.timedDisposableType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
class TDEventConfiguration {

    TDEventFacade timedDisposableEventFacade() {
        InMemoryTDEventRepository repository = new InMemoryTDEventRepository();
        return timedDisposableEventFacade(repository);
    }

    @Bean
    TDEventFacade timedDisposableEventFacade(TDEventRepository eventRepository) {
        TDEventCreator eventCreator = new TDEventCreator();
        return new TDEventFacade(eventCreator, eventRepository);
    }

    static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
}
