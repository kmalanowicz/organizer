package com.example

import com.example.base.IntegrationSpec
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OrganizerAcceptanceSpec extends IntegrationSpec {

    // TD - timed disposable
    // UD - untimed disposable
    // TC - timed constant
    // UC - untimed constant
    @WithMockUser
    def "should add specific event and get it back"() {
        when: "we add a new TD event"
            ResultActions postTDResult = mockMvc.perform(
                post("/event/TD")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                       {
                           "name": "wizyta u lekarza",
                           "description": "wizyta kontrolna w zwiazku z przeziebieniem",
                           "tag": "ZDROWIE",
                           "startDateTime": "2017-01-10T18:06:49Z",
                           "endDateTime": "2017-01-10T19:06:49Z"
                       }"""
                ))

        then: "we get status 200"
            postTDResult.andExpect(status().isOk())


        when: "we add a new UD event"
        ResultActions postUDResult = mockMvc.perform(
                post("/event/UD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                       {
                           "name": "zadanie z PJN",
                           "description": "odpowiedzi na pytanie o date urodzin i smierci znanych osob",
                           "tag": "UCZELNIA",
                           "deadlineDateTime": "2017-01-10T20:06:49Z",
                           "howLongItTakes": "02:00"
                       }"""
                ))

        then: "we get status 200"
        postUDResult.andExpect(status().isOk())

        when: "we add a new TC event"
        ResultActions postTCResult = mockMvc.perform(
                post("/event/TC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                       {
                           "name": "wyklad z SWB",
                           "description": "",
                           "tag": "UCZELNIA",
                           "schedule": [
                            {
                                "dayOfWeek" : "MONDAY",
                                "time" : "01:00",
                                "duration" : "01:00"
                            },
                            {
                                "dayOfWeek" : "SATURDAY",
                                "time" : "02:00",
                                "duration" : "12:00"
                            }
                           ],
                           "frequencyInMonth": "4",
                           "startPeriodDate": "01:10:2016",
                           "endPeriodDate": "16:02:2017"
                       }"""
                ))

        then: "we get status 200"
        postTCResult.andExpect(status().isOk())

        when: "we add a new UC event"
        ResultActions postUCResult = mockMvc.perform(
                post("/event/UC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                       {
                           "name": "cwiczenia na trampolinie",
                           "description": "",
                           "tag": "SPORT",
                           "typeOfTimeInterval": "WEEK",
                           "timesInInterval": 3,
                           "startPeriodDate": "06:01:2017",
                           "endPeriodDate": "06:03:2017",
                           "checked": false
                       }"""
                ))

        then: "we get status 200"
        postUCResult.andExpect(status().isOk())

        when: "we get UC events"
        ResultActions getUCResult = mockMvc.perform(get("/events/UC"))

        then: "the event we have added is returned"
        getUCResult
                .andExpect(status().isOk())
        String ucEventExpectedResponseAsJson = """
                 [
                   {
                       "name": "cwiczenia na trampolinie",
                       "description": "",
                       "tag": "SPORT",
                       "typeOfTimeInterval": "WEEK",
                       "timesInInterval": 3,
                       "startPeriodDate": "06:01:2017",
                       "endPeriodDate": "06:03:2017",
                       "checked": false
                   }
                 ]
               """
        slurpResponseBody(getUCResult) == asJson(ucEventExpectedResponseAsJson)

        when: "we get UD events"
        ResultActions getUDResult = mockMvc.perform(get("/events/UD"))

        then: "the event we have added is returned"
        getUDResult
                .andExpect(status().isOk())
        String udEventExpectedResponseAsJson = """
                [
                  {
                       "name": "zadanie z PJN",
                       "description": "odpowiedzi na pytanie o date urodzin i smierci znanych osob",
                       "tag": "UCZELNIA",
                       "deadlineDateTime": "2017-01-10T20:06:49",
                       "howLongItTakes": "02:00:00"
                  }
                ]
              """
        slurpResponseBody(getUDResult) == asJson(udEventExpectedResponseAsJson)

        when: "we get TD events "
        ResultActions getTDResult = mockMvc.perform(get("/events/TD"))

        then: "the event we have added is returned"
        getTDResult
                .andExpect(status().isOk())
        String tdEventExpectedResponseAsJson = """
                [
                  {
                           "name": "wizyta u lekarza",
                           "description": "wizyta kontrolna w zwiazku z przeziebieniem",
                           "tag": "ZDROWIE",
                           "startDateTime": "2017-01-10T18:06:49",
                           "endDateTime": "2017-01-10T19:06:49"
                  }
                ]
              """
        slurpResponseBody(getTDResult) == asJson(tdEventExpectedResponseAsJson)

        when: "we get TC events "
        ResultActions getTCResult = mockMvc.perform(get("/events/TC"))

        then: "the event we have added is returned"
        getTCResult
                .andExpect(status().isOk())
        String tcEventExpectedResponseAsJson = """
                [
                  {
                           "name": "wyklad z SWB",
                           "description": "",
                           "tag": "UCZELNIA",
                           "schedule": [
                            {
                                "dayOfWeek" : "MONDAY",
                                "time" : "01:00:00",
                                "duration" : "01:00:00"
                            },
                            {
                                "dayOfWeek" : "SATURDAY",
                                "time" : "02:00:00",
                                "duration" : "12:00:00"
                            }
                           ],
                           "frequencyInMonth": "4",
                           "startPeriodDate": "01:10:2016",
                           "endPeriodDate": "16:02:2017"
                  }
                ]
              """
        slurpResponseBody(getTCResult) == asJson(tcEventExpectedResponseAsJson)
    }
}
