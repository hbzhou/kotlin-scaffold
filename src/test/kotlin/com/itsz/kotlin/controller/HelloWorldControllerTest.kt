package com.itsz.kotlin.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [HelloWorldController::class], excludeAutoConfiguration = [SecurityAutoConfiguration::class])
class HelloWorldControllerTest {
    @MockkBean
    lateinit var helloWorldService: HelloWorldService

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun `test hello world api`() {
        every { helloWorldService.hello() } returns "what's up"
        mvc.get("/api/hello")
            .andExpect {
                status { isOk() }
                content { string("what's up") }
            }


    }
}