package com.example.billingservice.controller;

import com.example.billingservice.model.Invoice;
import com.example.billingservice.service.InvoiceService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final InvoiceService invoiceService;

    public BillingController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }
}