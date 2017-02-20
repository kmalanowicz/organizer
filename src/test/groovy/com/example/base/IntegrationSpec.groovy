package com.example.base

import com.example.OrganizerApplication
import groovy.json.JsonSlurper
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@ActiveProfiles(["test"])
@ContextConfiguration
@SpringBootTest(
        classes = [OrganizerApplication],
        webEnvironment = WebEnvironment.MOCK
)
@Transactional
@Rollback
abstract class IntegrationSpec extends Specification {
    @Autowired
    protected WebApplicationContext webApplicationContext

    MockMvc mockMvc

    @Before
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .apply(SecurityMockMvcConfigurers.springSecurity())
                        .build()
    }

    static Object asJson(String s) {
        JsonSlurper slurper = new JsonSlurper()
        return slurper.parseText(s)
    }

    static Object slurpResponseBody(ResultActions fetchResultActions) {
        String responseAsString = fetchResultActions.andReturn()
                .getResponse()
                .getContentAsString()
        JsonSlurper slurper = new JsonSlurper()
        Object response = slurper.parseText(responseAsString)
        return response
    }
}
