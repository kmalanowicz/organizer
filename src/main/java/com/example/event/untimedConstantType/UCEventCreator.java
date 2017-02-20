package com.example.event.untimedConstantType;

/**
 * Created by Lama on 2017-01-10.
 */
class UCEventCreator {
    public UCEvent createUC(UCEventFacade.UCEventDto eventDto) {
        return UCEvent.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .tag(eventDto.getTag())
                .startPeriodDate(eventDto.getStartPeriodDate())
                .endPeriodDate(eventDto.getEndPeriodDate())
                .typeOfTimeInterval(eventDto.getTypeOfTimeInterval())
                .checked(eventDto.isChecked())
                .timesInInterval(eventDto.getTimesInInterval())
                .build();
    }
}
