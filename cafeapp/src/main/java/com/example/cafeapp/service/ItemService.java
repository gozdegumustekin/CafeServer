package com.example.cafeapp.service;

import com.example.cafeapp.model.CafeItem;
import com.example.cafeapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Yeni item'ı veritabanına ekle
    public CafeItem addItem(CafeItem item) {
        return itemRepository.save(item);  // Veritabanına kaydeder
    }

    // Veritabanındaki tüm item'ları getir
    public List<CafeItem> getAllItems() {
        return itemRepository.findAll();
    }
}
