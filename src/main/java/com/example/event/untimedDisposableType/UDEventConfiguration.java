package com.example.event.untimedDisposableType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
class UDEventConfiguration {

    UDEventFacade untimedDisposableEventFacade() {
        InMemoryUDEventRepository repository = new InMemoryUDEventRepository();
        return untimedDisposableEventFacade(repository);
    }

    @Bean
    UDEventFacade untimedDisposableEventFacade(UDEventRepository eventRepository) {
        UDEventCreator eventCreator = new UDEventCreator();
        return new UDEventFacade(eventCreator, eventRepository);
    }

    static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
}
