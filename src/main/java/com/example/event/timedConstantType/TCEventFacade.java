package com.example.event.timedConstantType;

import com.example.event.timedDisposableType.TDEventFacade;
import com.example.event.typeInterfaces.ConstantType;
import com.example.event.typeInterfaces.EventType;
import com.example.event.typeInterfaces.TimedType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Transactional
public class TCEventFacade {
    private TCEventCreator tcEventCreator;
    private TCEventRepository tcEventRepository;

    public TCEventFacade(TCEventCreator tcEventCreator, TCEventRepository tcEventRepository) {
        this.tcEventCreator = tcEventCreator;
        this.tcEventRepository = tcEventRepository;
    }

    public TCEventDto addTCEvent(TCEventDto tcEventDto) {
        validate(tcEventDto);
        TCEvent event = tcEventCreator.createTC(tcEventDto);
        event = tcEventRepository.saveAndFlush(event);
        return event.dto();
    }

    private void validate(TCEventDto tcEventDto) {
        if(!tcEventDto.isValid()) {
            throw new IllegalArgumentException("Blad walidacji - data rozpoczecia jest pozniejsza, niz data zakonczenia");
        }
    }

    public List<TCEventDto> getTCEvents() {
        List<TCEvent> events = tcEventRepository.findAll();
        List<TCEventDto> eventDtos = events.stream()
                .map(TCEvent::dto)
                .collect(toList());
        return eventDtos;
    }

    @Value
    public static class TCEventDto implements TimedType, ConstantType, EventType {
        @NotNull
        private String name;
        private String description;
        @Size(max = 15)
        @NotNull
        private String tag;
        @Pattern(regexp = "[1-4]")
        @NotNull
        private String frequencyInMonth;
        @NotNull
        private List<PartOfSchedule> schedule;
        @JsonFormat(pattern = "dd:MM:yyyy")
        @NotNull
        private LocalDate startPeriodDate;
        @JsonFormat(pattern = "dd:MM:yyyy")
        @NotNull
        private LocalDate endPeriodDate;

        private boolean isValid() {
            return startPeriodDate.isBefore(endPeriodDate);
        }
    }
}
