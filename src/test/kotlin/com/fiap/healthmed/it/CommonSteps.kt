package com.fiap.healthmed.it

import io.cucumber.java.Before
import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.jdbc.core.JdbcTemplate

class CommonSteps: IntegrationTest() {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @LocalServerPort
    private val port: Int? = null

    @Before
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port"
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    @Before("@database")
    fun setupDatabase() {
        jdbcTemplate.execute("""
            TRUNCATE TABLE medical_appointment RESTART IDENTITY CASCADE;
            TRUNCATE TABLE medical_record RESTART IDENTITY CASCADE;
            TRUNCATE TABLE doctor RESTART IDENTITY CASCADE;
            TRUNCATE TABLE patient RESTART IDENTITY CASCADE;
        """.trimIndent()
        )
    }
}
