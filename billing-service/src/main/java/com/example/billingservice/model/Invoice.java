package com.example.billingservice.model;

public class Invoice {

    private String invoiceId;
    private String orderId;
    private String item;
    private int quantity;
    private double totalAmount;

    public Invoice() {
    }

    public Invoice(String invoiceId, String orderId, String item, int quantity, double totalAmount) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}