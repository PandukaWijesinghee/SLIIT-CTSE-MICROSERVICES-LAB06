package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.InventoryUpdate;
import com.example.inventoryservice.service.InventoryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryUpdate> getInventoryUpdates() {
        return inventoryService.getUpdates();
    }
}