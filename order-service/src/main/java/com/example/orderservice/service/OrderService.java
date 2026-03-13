package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final String TOPIC_NAME = "order-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    public OrderService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void createOrder(Order order) {
        orders.add(order);
        String payload = toJson(order);
        kafkaTemplate.send(TOPIC_NAME, order.getOrderId(), payload);
        log.info("Order created with id: {}", order.getOrderId());
        log.info("Published OrderCreated event to Kafka: {}", payload);
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    private String toJson(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Failed to serialize order event", exception);
        }
    }
}