package com.cliniccare.appointmentservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConfig {

    @Value("${cliniccare.rabbitmq.exchange}")
    private String exchange;

    @Value("${cliniccare.rabbitmq.queue}")
    private String queue;

    @Value("${cliniccare.rabbitmq.routingKey}")
    private String routingKey;

    @Bean
    public TopicExchange clinicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue clinicQueue() {
        return new Queue(queue, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(clinicQueue())
                .to(clinicExchange())
                .with(routingKey);
    }
}
