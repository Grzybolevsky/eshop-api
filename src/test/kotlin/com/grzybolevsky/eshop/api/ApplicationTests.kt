package com.grzybolevsky.eshop.api

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("tests", "dev")
class ApplicationTests {

    @Test
    @Suppress("EmptyFunctionBlock")
    fun contextLoads() {
    }
}
