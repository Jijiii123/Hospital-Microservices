package com.example.patientservice.events;

import com.example.patientservice.model.Patient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PatientEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${clinic.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${clinic.rabbitmq.patient.routing-key}")
    private String routingKey;

    public PatientEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPatientCreated(Patient patient) {
        PatientEvent event = buildEvent("CREATED", patient);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    public void publishPatientUpdated(Patient patient) {
        PatientEvent event = buildEvent("UPDATED", patient);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    public void publishPatientDeleted(Patient patient) {
        PatientEvent event = buildEvent("DELETED", patient);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    private PatientEvent buildEvent(String type, Patient patient) {
        return new PatientEvent(
                type,
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getAddress(),
                patient.getDateOfBirth(),
                LocalDateTime.now()
        );
    }
}
