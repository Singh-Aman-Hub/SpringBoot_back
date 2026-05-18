package com.example.paulfever.repository;

import com.example.paulfever.entity.Item;
import com.example.paulfever.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByType(ItemType type);
    List<Item> findByCategory(String category);
}
