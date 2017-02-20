package com.example.event.timedConstantType

import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.List

import static com.example.event.timedConstantType.TCEventFacadeSpec.DtoEventForTestBuilder.starting;


class TCEventFacadeSpec extends Specification {

    TCEventFacade tcEventFacade = new TCEventConfiguration()
            .timedConstantEventFacade();

    def "added event should not start after it ends"() {
        given:
        Instant after = Instant.now()
        Instant before = after.minus(1, ChronoUnit.DAYS)
        TCEventFacade.TCEventDto badEvent = starting(after).ending(before)

        when:
        tcEventFacade.addTCEvent(badEvent)

        then:
        thrown(IllegalArgumentException)
    }

    def "should add event that was given"() {
        given:
        Instant after = Instant.now()
        Instant before = after.minus(1, ChronoUnit.DAYS)
        TCEventFacade.TCEventDto goodEvent = starting(before).ending(after)
        expect:
        tcEventFacade.addTCEvent(goodEvent) == goodEvent
    }

    def "should get event that was added"() {
        given: "we have a good event dto"
        TCEventFacade.TCEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
        tcEventFacade.addTCEvent(createdEventDto)

        when: "we ask for all events"
        List<TCEventFacade.TCEventDto> retrievedEvents = tcEventFacade.getTCEvents()

        then: "we get the even that we have added"
        retrievedEvents.size() == 1
        retrievedEvents.first().name == createdEventDto.name
    }

    static class SampleEvents {
        static TCEventFacade.TCEventDto event = starting(Instant.now()).ending(Instant.now().plus(1, ChronoUnit.DAYS))
    }

    static class DtoEventForTestBuilder {
        Instant start

        static DtoEventForTestBuilder starting(Instant start) {
            DtoEventForTestBuilder builderHelper = new DtoEventForTestBuilder()
            builderHelper.start = start
            return builderHelper;
        }

        TCEventFacade.TCEventDto ending(Instant end) {
            ZoneId zoneId = ZoneId.systemDefault()
            List<PartOfSchedule> list;
            return new TCEventFacade.TCEventDto("name", "", "TAG", "", list, LocalDateTime.ofInstant(start, zoneId).toLocalDate(), LocalDateTime.ofInstant(end, zoneId).toLocalDate())
        }
    }

}
