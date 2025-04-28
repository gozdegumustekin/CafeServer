package com.example.cafeapp.repository;

import com.example.cafeapp.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByIsUsedFalseAndExpiryDateAfter(LocalDate date);

    Optional<Coupon> findByCode(String s);
}
