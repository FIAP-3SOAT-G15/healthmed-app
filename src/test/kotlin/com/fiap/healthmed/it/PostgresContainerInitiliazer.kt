package com.fiap.healthmed.it

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait.forListeningPort

class PostgreSQLContainerInitializer :
    ApplicationContextInitializer<ConfigurableApplicationContext>,
    PostgreSQLContainer<PostgreSQLContainerInitializer>("postgres:15.4") {
    companion object {
        private val instance: PostgreSQLContainerInitializer =
            PostgreSQLContainerInitializer()
                .withDatabaseName("database")
                .withUsername("database")
                .withPassword("database")
                .waitingFor(forListeningPort())
    }

    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        instance.start()
        TestPropertyValues.of(
            "spring.datasource.url=${instance.jdbcUrl}",
            "spring.datasource.username=${instance.username}",
            "spring.datasource.password=${instance.password}",
        ).applyTo(configurableApplicationContext)
    }
}
