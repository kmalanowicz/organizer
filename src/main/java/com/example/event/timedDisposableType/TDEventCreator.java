package com.example.event.timedDisposableType;

/**
 * Created by Lama on 2017-01-10.
 */
class TDEventCreator {
    public TDEvent createTD(TDEventFacade.TDEventDto eventDto) {
        return TDEvent.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .tag(eventDto.getTag())
                .startDateTime(eventDto.getStartDateTime())
                .endDateTime(eventDto.getEndDateTime())
                .build();
    }
}
