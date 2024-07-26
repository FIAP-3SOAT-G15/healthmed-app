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
        title = "Health&Med MVP",
        version = "1.0.0",
        description = "Demo API para sistema de consultas médicas online",
        contact = Contact(
            name = "Grupo 15",
            url = "http://fiap-3soat-g15-healthmed.s3-website-us-east-1.amazonaws.com",
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
