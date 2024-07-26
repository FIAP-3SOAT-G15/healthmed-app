package com.fiap.healthmed.it

import com.fiap.healthmed.adapter.gateway.PatientGateway
import com.fiap.healthmed.domain.Patient
import com.fiap.healthmed.driver.web.request.PatientRequest
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

class RegisterPatientIntegrationTest: IntegrationTest() {

    @Autowired
    lateinit var patientRepository: PatientGateway

    private lateinit var patientRequest: PatientRequest
    private lateinit var response: Response

    @Given("valid data for patient")
    fun validDataForPatient() {
        patientRequest = createPatientRequest()
    }

    @When("request to register patient")
    fun requestToRegisterPatient() {
        response = given()
            .contentType(ContentType.JSON)
            .body(patientRequest)
            .`when`()
            .post("/healthmed/patient")
    }

    @Then("patient should be registered")
    fun patientShouldBeRegistered() {
        val persistedPatient = patientRepository.get(patientRequest.document)

        assertThat(persistedPatient).isEqualTo(
            Patient(
                document = patientRequest.document,
                name = patientRequest.name,
                email = patientRequest.email,
                phoneNumber = patientRequest.phoneNumber,
                zipCode = patientRequest.zipCode,
                address = patientRequest.address,
            )
        )

        response
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(
                "document", equalTo(patientRequest.document),
                "name", equalTo(patientRequest.name),
                "email", equalTo(patientRequest.email),
                "phoneNumber", equalTo(patientRequest.phoneNumber),
                "zipCode", equalTo(patientRequest.zipCode),
                "address", equalTo(patientRequest.address),
            )
    }
}
