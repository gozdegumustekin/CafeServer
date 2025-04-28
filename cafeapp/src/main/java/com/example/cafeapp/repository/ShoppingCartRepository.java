package com.example.cafeapp.repository;

import com.example.cafeapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    // Belirli bir kullanıcıya ait tüm sepeti getir
    List<ShoppingCart> findByUserId(Long userId);

    // Sepetten bir item'ı silmek için, item_id ve user_id'ye göre arama
    Optional<ShoppingCart> findByUserIdAndItemId(Long userId, Long itemId);
}
