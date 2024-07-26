CREATE TABLE IF NOT EXISTS patient
(
    patient_document VARCHAR(20)  NOT NULL PRIMARY KEY,
    patient_name     VARCHAR(255) NOT NULL,
    patient_email    VARCHAR(255) NOT NULL,
    patient_phone    VARCHAR(20)  NOT NULL,
    patient_zip_code VARCHAR(20)  NOT NULL,
    patient_address  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor
(
    doctor_crm               VARCHAR(20)    NOT NULL PRIMARY KEY,
    doctor_name              VARCHAR(255)   NOT NULL,
    doctor_email             VARCHAR(255)   NOT NULL,
    doctor_phone             VARCHAR(20)    NOT NULL,
    doctor_document          VARCHAR(20)    NOT NULL,
    doctor_specialty         VARCHAR(255)   NOT NULL,
    doctor_available_times   VARCHAR(2000)  NOT NULL,
    doctor_appointment_price NUMERIC(15, 2) NOT NULL,
    doctor_service_zip_code  VARCHAR(20)    NOT NULL,
    doctor_service_address   VARCHAR(255)   NOT NULL
);

CREATE TABLE IF NOT EXISTS medical_appointment
(
    medical_appointment_number                                SERIAL       NOT NULL PRIMARY KEY,
    medical_appointment_doctor_crm                            VARCHAR(20)  NOT NULL,
    medical_appointment_patient_document                      VARCHAR(20)  NOT NULL,
    medical_appointment_expected_start_time                   TIMESTAMP    NOT NULL,
    medical_appointment_estimated_time_spent_in_minutes       INTEGER      DEFAULT 50,
    medical_appointment_status                                VARCHAR(255) NOT NULL,
    medical_appointment_status_changed                        TIMESTAMP,
    medical_appointment_justification_cancellation_by_patient VARCHAR(2000),
    CONSTRAINT fk_medical_appointment_doctor_number
        FOREIGN KEY (medical_appointment_doctor_crm)
        REFERENCES doctor(doctor_crm),
    CONSTRAINT fk_medical_appointment_patient_number
        FOREIGN KEY(medical_appointment_patient_document)
        REFERENCES patient(patient_document)
);

CREATE TABLE IF NOT EXISTS medical_record
(
    medical_record_number                     SERIAL NOT NULL PRIMARY KEY,
    medical_record_medical_appointment_number SERIAL NOT NULL,
    medical_record_doctor_crm                 VARCHAR(20)   NOT NULL,
    medical_record_patient_document           VARCHAR(20)   NOT NULL,
    medical_record_provider                   VARCHAR(255)  NOT NULL DEFAULT 'S3',
    medical_record_file_location              VARCHAR(2000) NOT NULL,
    medical_record_file_name                  VARCHAR(2000) NOT NULL,
    CONSTRAINT fk_medical_record_medical_appointment_number
        FOREIGN KEY(medical_record_medical_appointment_number)
        REFERENCES medical_appointment(medical_appointment_number)
);
