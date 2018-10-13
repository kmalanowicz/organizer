package com.example.event.timedDisposableType

import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

import static com.example.event.timedDisposableType.TDEventFacadeSpec.DtoEventForTestBuilder.starting

class TDEventFacadeSpec extends Specification {

    TDEventFacade tdEventFacade = new TDEventConfiguration()
                                        .timedDisposableEventFacade();

    def "added event should not start after it ends"() {
        given:
            Instant after = Instant.now()
            Instant before = after.minus(1, ChronoUnit.DAYS)
            TDEventFacade.TDEventDto badEvent = starting(after).ending(before)

        when:
            tdEventFacade.addTDEvent(badEvent)

        then:
            thrown(IllegalArgumentException)
    }

    def "should add event that was given"() {
        given:
            Instant after = Instant.now()
            Instant before = after.minus(1, ChronoUnit.DAYS)
            TDEventFacade.TDEventDto goodEvent = starting(before).ending(after)
        expect:
            tdEventFacade.addTDEvent(goodEvent) == goodEvent
    }

    def "should get event from events list that was added"() {
        given: "we have a good event dto"
            TDEventFacade.TDEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
            tdEventFacade.addTDEvent(createdEventDto)

        when: "we ask for all events"
            List<TDEventFacade.TDEventDto> retrievedEvents = tdEventFacade.getTDEvents()

        then: "we get the even that we have added"
            retrievedEvents.size() == 1
            retrievedEvents.first().name == createdEventDto.name
    }

    def "should get event that was added by its name"() {
        given: "we have a good event dto"
        TDEventFacade.TDEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
        tdEventFacade.addTDEvent(createdEventDto)

        when: "we ask for all events"
        TDEventFacade.TDEventDto retrievedEvent = tdEventFacade.getTDEventByName(createdEventDto.name)

        then: "we get the even that we have added"
        retrievedEvent.name == createdEventDto.name
    }

    static class SampleEvents {
        static TDEventFacade.TDEventDto event = starting(Instant.now()).ending(Instant.now().plus(1, ChronoUnit.DAYS))
    }

    static class DtoEventForTestBuilder {
        Instant start

        static DtoEventForTestBuilder starting(Instant start) {
            DtoEventForTestBuilder builderHelper = new DtoEventForTestBuilder()
            builderHelper.start = start
            return builderHelper;
        }

        TDEventFacade.TDEventDto ending(Instant end) {
            ZoneId zoneId = ZoneId.systemDefault()
            return new TDEventFacade.TDEventDto("event",
                    "",
                    "INFO", LocalDateTime.ofInstant(start, zoneId), LocalDateTime.ofInstant(end, zoneId))
        }
    }

}
