package com.fiap.healthmed

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    info =
    Info(
        title = "HealthMed App",
        version = "1.0.0",
        description = "MVP telemedicine app",
        contact = Contact(
            name = "Grupo 15",
            url = "http://fiap-3soat-g15.s3-website-us-east-1.amazonaws.com",
        ),
    ),
    servers = [
        Server(url = "/"),
    ],
)
class HealthMedApp

fun main(args: Array<String>) {
    runApplication<HealthMedApp>(*args)
}
