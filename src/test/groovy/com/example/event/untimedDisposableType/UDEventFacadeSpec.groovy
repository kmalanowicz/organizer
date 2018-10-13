package com.example.event.untimedDisposableType

import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class UDEventFacadeSpec extends Specification {

    UDEventFacade udEventFacade = new UDEventConfiguration()
            .untimedDisposableEventFacade()

    def "should add event that was given"() {
        given:
        UDEventFacade.UDEventDto goodEvent = SampleEvents.event

        expect:
        udEventFacade.addUDEvent(goodEvent) == goodEvent
    }

    def "should get event from events list that was added"() {
        given: "we have a good event dto"
        UDEventFacade.UDEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
        udEventFacade.addUDEvent(createdEventDto)

        when: "we ask for all events"
        List<UDEventFacade.UDEventDto> retrievedEvents = udEventFacade.getUDEvents()

        then: "we get the even that we have added"
        retrievedEvents.size() == 1
        retrievedEvents.first().name == createdEventDto.name
    }

    def "should get event that was added"() {
        given: "we have a good event dto"
        UDEventFacade.UDEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
        udEventFacade.addUDEvent(createdEventDto)

        when: "we ask for event by its name"
        UDEventFacade.UDEventDto retrievedEvent = udEventFacade.getUDEventByName(createdEventDto.name)

        then: "we get the event that we have added"
        retrievedEvent.name == createdEventDto.name
    }

    static class SampleEvents {
        static UDEventFacade.UDEventDto event = new UDEventFacade.UDEventDto("name","","TAG", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()), LocalTime.of(20,0))
    }

}
