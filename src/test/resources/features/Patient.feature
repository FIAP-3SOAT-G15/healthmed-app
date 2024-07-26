Feature: Patient

    @database
    Scenario: Registering patient
        Given valid data for patient
        When request to register patient
        Then patient should be registered
