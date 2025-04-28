package com.example.cafeapp.controller;

import com.example.cafeapp.model.CafeItem;
import com.example.cafeapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*") // Flutter'dan gelen isteklere izin verir
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Yeni item eklemek için API
    @PostMapping("/add")
    public CafeItem addItem(@RequestBody CafeItem item) {
        return itemService.addItem(item);  // Yeni item'ı veritabanına ekler
    }

    // Tüm item'ları çekmek için API
    @GetMapping("/getAll")
    public List<CafeItem> getAllItems() {
        return itemService.getAllItems();
    }
}
