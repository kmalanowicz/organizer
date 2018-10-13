package com.example.event.untimedDisposableType;

import com.example.event.typeInterfaces.DisposableType;
import com.example.event.typeInterfaces.EventType;
import com.example.event.typeInterfaces.UntimedType;
import lombok.Value;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UDEventFacade {
    private UDEventCreator udEventCreator;
    private UDEventRepository udEventRepository;

    public UDEventFacade(UDEventCreator udEventCreator, UDEventRepository udEventRepository) {
        this.udEventCreator = udEventCreator;
        this.udEventRepository = udEventRepository;
    }

    public UDEventDto addUDEvent(UDEventDto eventDto) {
        UDEvent event = udEventCreator.createUD(eventDto);
        event = udEventRepository.saveAndFlush(event);
        return event.dto();

    }

    public List<UDEventDto> getUDEvents() {
        List<UDEvent> events = udEventRepository.findAll();
        List<UDEventDto> eventDtos = events.stream()
                .map(UDEvent::dto)
                .collect(toList());
        return eventDtos;
    }

    public UDEventDto getUDEventByName(String name) {
        UDEvent event = udEventRepository.findByName(name);
        return event.dto();
    }

    @Value
    public static class UDEventDto implements UntimedType, DisposableType, EventType {
        @NotNull
        private String name;
        private String description;
        @Size(max = 15)
        @NotNull
        private String tag;
        private LocalDateTime deadlineDateTime;
        private LocalTime howLongItTakes;

    }
}
