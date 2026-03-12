package com.example.inventoryservice.messaging;

import com.example.inventoryservice.model.InventoryUpdate;
import com.example.inventoryservice.model.OrderEvent;
import com.example.inventoryservice.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    public OrderEventConsumer(ObjectMapper objectMapper, InventoryService inventoryService) {
        this.objectMapper = objectMapper;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    public void consume(String message) {
        OrderEvent event = parse(message);
        inventoryService.addUpdate(new InventoryUpdate(
                event.getOrderId(),
                event.getItem(),
                event.getQuantity(),
                "Inventory successfully updated"));
        log.info("Received order event: {}", message);
        log.info(
                "Processing inventory update for Order ID: {}, Item: {}, Quantity: {}",
                event.getOrderId(),
                event.getItem(),
                event.getQuantity());
        log.info("Inventory successfully updated for order: {}", event.getOrderId());
    }

    private OrderEvent parse(String message) {
        try {
            return objectMapper.readValue(message, OrderEvent.class);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Failed to parse order event", exception);
        }
    }
}