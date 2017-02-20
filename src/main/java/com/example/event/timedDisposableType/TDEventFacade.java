package com.example.event.timedDisposableType;

import com.example.event.typeInterfaces.DisposableType;
import com.example.event.typeInterfaces.EventType;
import com.example.event.typeInterfaces.TimedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Lama on 2017-01-10.
 */
public class TDEventFacade {
    private TDEventCreator tdEventCreator;
    private TDEventRepository tdEventRepository;

    public TDEventFacade(TDEventCreator tdEventCreator, TDEventRepository tdEventRepository) {
        this.tdEventRepository = tdEventRepository;
        this.tdEventCreator = tdEventCreator;
    }

    public TDEventDto addTDEvent(TDEventDto tdEventDto) {
        validate(tdEventDto);
        TDEvent event = tdEventCreator.createTD(tdEventDto);
        event = tdEventRepository.saveAndFlush(event);
        return event.dto();
    }

    private void validate(TDEventDto tdEventDto) {
        if(!tdEventDto.isValid()) {
            throw new IllegalArgumentException("Blad walidacji - data rozpoczecia jest pozniejsza, niz data zakonczenia");
        }
    }

    public List<TDEventDto> getTDEvents() {
        List<TDEvent> events = tdEventRepository.findAll();
        List<TDEventDto> eventDtos = events.stream()
                .map(TDEvent::dto)
                .collect(toList());
        return eventDtos;
    }

    @AllArgsConstructor
    @Value
    @Builder
    public static class TDEventDto implements TimedType, EventType, DisposableType {
        @NotNull
        private String name;
        private String description;
        @Size (max = 15)
        @NotNull
        private String tag;
        @NotNull
        private LocalDateTime startDateTime;
        @NotNull
        private LocalDateTime endDateTime;

        private boolean isValid() {
            return startDateTime.isBefore(endDateTime);
        }
    }
}
