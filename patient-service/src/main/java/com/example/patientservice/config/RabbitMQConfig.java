package com.example.patientservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${clinic.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${clinic.rabbitmq.patient.queue}")
    private String patientQueueName;

    @Value("${clinic.rabbitmq.patient.routing-key}")
    private String patientRoutingKey;

    @Bean
    public TopicExchange clinicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue patientQueue() {
        return new Queue(patientQueueName, true); // durable = true
    }

    @Bean
    public Binding patientBinding(Queue patientQueue, TopicExchange clinicExchange) {
        return BindingBuilder
                .bind(patientQueue)
                .to(clinicExchange)
                .with(patientRoutingKey);
    }
}
