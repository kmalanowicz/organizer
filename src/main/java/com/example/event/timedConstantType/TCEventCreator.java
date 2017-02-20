package com.example.event.timedConstantType;

/**
 * Created by Lama on 2017-01-10.
 */
class TCEventCreator {
    public TCEvent createTC(TCEventFacade.TCEventDto eventDto) {
        return TCEvent.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .tag(eventDto.getTag())
                .frequencyInMonth(eventDto.getFrequencyInMonth())
                .schedule(eventDto.getSchedule())
                .startPeriodDate(eventDto.getStartPeriodDate())
                .endPeriodDate(eventDto.getEndPeriodDate())
                .build();
    }
}
