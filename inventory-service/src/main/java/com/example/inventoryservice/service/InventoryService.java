package com.example.inventoryservice.service;

import com.example.inventoryservice.model.InventoryUpdate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final List<InventoryUpdate> updates = new CopyOnWriteArrayList<>();

    public void addUpdate(InventoryUpdate update) {
        updates.add(update);
    }

    public List<InventoryUpdate> getUpdates() {
        return List.copyOf(updates);
    }
}