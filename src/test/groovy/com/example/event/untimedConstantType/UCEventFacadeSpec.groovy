package com.example.event.untimedConstantType

import com.example.event.untimedConstantType.UCEventConfiguration
import com.example.event.untimedConstantType.UCEventFacade
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

import static com.example.event.untimedConstantType.UCEventFacadeSpec.DtoEventForTestBuilder.starting


class UCEventFacadeSpec extends Specification {

    UCEventFacade ucEventFacade = new UCEventConfiguration()
            .untimedConstantEventFacade();

    def "added event should not start after it ends"() {
        given:
        Instant after = Instant.now()
        Instant before = after.minus(1, ChronoUnit.DAYS)
        UCEventFacade.UCEventDto badEvent = starting(after).ending(before)

        when:
        ucEventFacade.addUCEvent(badEvent)

        then:
        thrown(IllegalArgumentException)
    }

    def "should add event that was given"() {
        given:
        Instant after = Instant.now()
        Instant before = after.minus(1, ChronoUnit.DAYS)
        UCEventFacade.UCEventDto goodEvent = starting(before).ending(after)

        expect:
        ucEventFacade.addUCEvent(goodEvent) == goodEvent
    }

    def "should get event that was added"() {
        given: "we have a good event dto"
        UCEventFacade.UCEventDto createdEventDto = SampleEvents.event

        and: "we add it to facade"
        ucEventFacade.addUCEvent(createdEventDto)

        when: "we ask for all events"
        List<UCEventFacade.UCEventDto> retrievedEvents = ucEventFacade.getUCEvents()

        then: "we get the even that we have added"
        retrievedEvents.size() == 1
        retrievedEvents.first().name == createdEventDto.name
    }

    static class SampleEvents {
        static UCEventFacade.UCEventDto event = starting(Instant.now()).ending(Instant.now().plus(1, ChronoUnit.DAYS))
    }

    static class DtoEventForTestBuilder {
        Instant start
        static DtoEventForTestBuilder starting(Instant start) {
            DtoEventForTestBuilder builderHelper = new DtoEventForTestBuilder()
            builderHelper.start = start
            return builderHelper;
        }

        UCEventFacade.UCEventDto ending(Instant end) {
            ZoneId zoneId = ZoneId.systemDefault()
            return new UCEventFacade.UCEventDto("name","","TAG", LocalDateTime.ofInstant(start, zoneId).toLocalDate(), LocalDateTime.ofInstant(end, zoneId).toLocalDate(),true, "WEEK" as TypeOfTimeInterval, 3)
        }
    }

}
