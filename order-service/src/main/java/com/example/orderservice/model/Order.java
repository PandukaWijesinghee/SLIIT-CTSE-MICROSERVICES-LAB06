package com.example.orderservice.model;

public class Order {

    private String orderId;
    private String item;
    private int quantity;
    private double unitPrice;

    public Order() {
    }

    public Order(String orderId, String item, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}