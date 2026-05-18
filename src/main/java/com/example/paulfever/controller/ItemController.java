package com.example.paulfever.controller;

import com.example.paulfever.entity.Item;
import com.example.paulfever.entity.ItemType;
import com.example.paulfever.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> reportItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.reportItem(item));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) ItemType type) {
        if (type != null) {
            return ResponseEntity.ok(itemService.getItemsByType(type));
        }
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return ResponseEntity.ok(itemService.updateItem(item));
    }
}
