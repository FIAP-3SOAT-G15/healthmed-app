package com.fiap.healthmed.it

import com.fiap.healthmed.adapter.gateway.DoctorGateway
import com.fiap.healthmed.domain.Doctor
import com.fiap.healthmed.driver.web.request.DoctorRequest
import com.fiap.healthmed.driver.web.request.toDomain
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class RegisterDoctorIntegrationTest: IntegrationTest() {

    @Autowired
    lateinit var doctorRepository: DoctorGateway

    private lateinit var doctorRequest: DoctorRequest
    private lateinit var response: Response

    @Given("valid data for doctor")
    fun validDataForDoctor() {
        doctorRequest = createDoctorRequest()
    }

    @When("request to register doctor")
    fun requestToRegisterDoctor() {
        response = given()
            .contentType(ContentType.JSON)
            .body(doctorRequest)
            .`when`()
            .post("/healthmed/doctor")
    }

    @Then("doctor should be registered")
    fun doctorShouldBeRegistered() {
        val persistedDoctor = doctorRepository.get(doctorRequest.crm)

        assertThat(persistedDoctor).isEqualTo(
            Doctor(
                crm = doctorRequest.crm,
                document = doctorRequest.document,
                specialty = doctorRequest.specialty,
                name = doctorRequest.name,
                email = doctorRequest.email,
                phoneNumber = doctorRequest.phoneNumber,
                serviceZipCode = doctorRequest.serviceZipCode,
                serviceAddress = doctorRequest.serviceAddress,
                availableTimes = doctorRequest.availableTimes.toDomain(),
                appointmentPrice = doctorRequest.appointmentPrice
            )
        )

        response
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(
                "crm", equalTo(doctorRequest.crm),
                "document", equalTo(doctorRequest.document),
                "specialty", equalTo(doctorRequest.specialty),
                "name", equalTo(doctorRequest.name),
                "email", equalTo(doctorRequest.email),
                "phoneNumber", equalTo(doctorRequest.phoneNumber),
                "serviceZipCode", equalTo(doctorRequest.serviceZipCode),
                "serviceAddress", equalTo(doctorRequest.serviceAddress),
                "availableTimes", equalTo(doctorRequest.availableTimes.toDomain()),
                "appointmentPrice", equalTo(doctorRequest.appointmentPrice),
            )
    }
}
