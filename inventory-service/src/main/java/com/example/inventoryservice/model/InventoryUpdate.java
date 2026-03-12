package com.example.inventoryservice.model;

public class InventoryUpdate {

    private String orderId;
    private String item;
    private int quantity;
    private String status;

    public InventoryUpdate() {
    }

    public InventoryUpdate(String orderId, String item, int quantity, String status) {
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}