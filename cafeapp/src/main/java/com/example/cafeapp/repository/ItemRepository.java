package com.example.cafeapp.repository;

import com.example.cafeapp.model.CafeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<CafeItem, Long> {
    // JpaRepository'yi kullanarak CRUD işlemleri otomatik olarak sağlanır.
}
