package com.example.paulfever.service;

import com.example.paulfever.entity.Item;
import com.example.paulfever.entity.ItemType;
import com.example.paulfever.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item reportItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByType(ItemType type) {
        return itemRepository.findByType(type);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
    
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}
