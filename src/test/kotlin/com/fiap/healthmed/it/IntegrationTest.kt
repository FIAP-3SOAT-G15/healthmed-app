package com.fiap.healthmed.it

import com.fiap.healthmed.WithPostgreSQL
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.Tag
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithPostgreSQL
@Tag("IntegrationTest")
class IntegrationTest
