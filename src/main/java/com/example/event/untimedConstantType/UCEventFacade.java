package com.example.event.untimedConstantType;

import com.example.event.typeInterfaces.ConstantType;
import com.example.event.typeInterfaces.EventType;
import com.example.event.typeInterfaces.UntimedType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * Created by Lama on 2017-01-10.
 */
public class UCEventFacade {
    private UCEventCreator ucEventCreator;
    private UCEventRepository ucEventRepository;

    public UCEventFacade(UCEventCreator ucEventCreator, UCEventRepository ucEventRepository) {
        this.ucEventCreator = ucEventCreator;
        this.ucEventRepository = ucEventRepository;
    }

    public UCEventDto getUCEventByName(String name) {
        UCEvent event = ucEventRepository.findByName(name);
        return event.dto();
    }

    public UCEventDto addUCEvent(UCEventDto eventDto) {
        validate(eventDto);
        UCEvent event = ucEventCreator.createUC(eventDto);
        event = ucEventRepository.saveAndFlush(event);
        return event.dto();
    }

    private void validate(UCEventDto eventDto) {
        if(!eventDto.isValid()) {
            throw new IllegalArgumentException("Blad walidacji - data rozpoczecia jest pozniejsza, niz data zakonczenia");
        }
    }

    public List<UCEventDto> getUCEvents() {
        List<UCEvent> events = ucEventRepository.findAll();
        List<UCEventDto> eventDtos = events.stream()
                .map(UCEvent::dto)
                .collect(toList());
        return eventDtos;
    }

    @Value
    public static class UCEventDto implements UntimedType, ConstantType, EventType {
        @NotNull
        private String name;
        private String description;
        @Size(max = 15)
        @NotNull
        private String tag;
        @JsonFormat(pattern = "dd:MM:yyyy")
        @NotNull
        private LocalDate startPeriodDate;
        @JsonFormat(pattern = "dd:MM:yyyy")
        @NotNull
        private LocalDate endPeriodDate;
        @NotNull
        private boolean checked;
        @NotNull
        private TypeOfTimeInterval typeOfTimeInterval;
        @Min(1)
        @NotNull
        private int timesInInterval;

        private boolean isValid() {
            return startPeriodDate.isBefore(endPeriodDate);
        }
    }
}
