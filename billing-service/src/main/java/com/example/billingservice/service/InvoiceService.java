package com.example.billingservice.service;

import com.example.billingservice.model.Invoice;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return List.copyOf(invoices);
    }
}