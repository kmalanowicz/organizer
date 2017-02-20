package com.example.event.untimedDisposableType;

/**
 * Created by Lama on 2017-01-10.
 */
class UDEventCreator {
    public UDEvent createUD(UDEventFacade.UDEventDto eventDto) {
        return UDEvent.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .tag(eventDto.getTag())
                .howLongItTakes(eventDto.getHowLongItTakes())
                .deadlineDateTime(eventDto.getDeadlineDateTime())
                .build();
    }
}
