package com.example.cafeapp.service;

import com.example.cafeapp.model.ShoppingCart;
import com.example.cafeapp.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    // Sepete yeni item ekleme
    public ShoppingCart addItemToCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    // Kullanıcıya ait tüm sepetteki item'ları getirme
    public List<ShoppingCart> getItemsInCart(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    // Sepetten bir item'ı silme
    public boolean removeItemFromCart(Long userId, Long itemId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserIdAndItemId(userId, itemId);
        if (shoppingCart.isPresent()) {
            shoppingCartRepository.delete(shoppingCart.get());
            return true; // Silindi
        } else {
            return false; // Item bulunamadı
        }
    }


    public Optional<ShoppingCart> findItemInCart(Long userId, Long itemId) {
        return shoppingCartRepository.findByUserIdAndItemId(userId, itemId);
    }
}
