package com.example.billingservice.messaging;

import com.example.billingservice.model.Invoice;
import com.example.billingservice.model.OrderEvent;
import com.example.billingservice.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    private final ObjectMapper objectMapper;
    private final InvoiceService invoiceService;

    public OrderEventConsumer(ObjectMapper objectMapper, InvoiceService invoiceService) {
        this.objectMapper = objectMapper;
        this.invoiceService = invoiceService;
    }

    @KafkaListener(topics = "order-topic", groupId = "billing-group")
    public void consume(String message) {
        OrderEvent event = parse(message);
        Invoice invoice = new Invoice(
                "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                event.getOrderId(),
                event.getItem(),
                event.getQuantity(),
                event.getQuantity() * event.getUnitPrice());
        invoiceService.addInvoice(invoice);
        log.info("Received order event for billing: {}", message);
        log.info(
                "Processing invoice generation for Order ID: {}, Item: {}, Quantity: {}, Total: {}",
                event.getOrderId(),
                event.getItem(),
                event.getQuantity(),
                invoice.getTotalAmount());
        log.info("Invoice {} generated successfully for order: {}", invoice.getInvoiceId(), event.getOrderId());
    }

    private OrderEvent parse(String message) {
        try {
            return objectMapper.readValue(message, OrderEvent.class);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Failed to parse order event", exception);
        }
    }
}