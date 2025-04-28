package com.example.cafeapp.controller;

import com.example.cafeapp.model.Coupon;
import com.example.cafeapp.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
@CrossOrigin(origins = "*")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    // 2. Sadece geçerli (tarihi geçmemiş, kullanılmamış) kuponları getir
    @GetMapping("/valid")
    public Optional<Coupon> getValidCoupons() {
        return couponRepository.findByCode(String.valueOf(LocalDate.now()));
    }

    // 3. Belirli bir kuponu kullan (isUsed = true yapar)
    @PutMapping("/use/{id}")
    public Coupon useCoupon(@PathVariable Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        coupon.setUsed(true);
        return couponRepository.save(coupon);
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(@PathVariable Long id, @RequestBody Coupon updatedCoupon) {
        return couponRepository.findById(id)
                .map(coupon -> {
                    coupon.setCode(updatedCoupon.getCode());
                    coupon.setDescription(updatedCoupon.getDescription());
                    coupon.setDiscountAmount(updatedCoupon.getDiscountAmount());
                    coupon.setPercentage(updatedCoupon.isPercentage());
                    coupon.setExpiryDate(updatedCoupon.getExpiryDate());
                    coupon.setUsed(updatedCoupon.isUsed());
                    return couponRepository.save(coupon);
                }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponRepository.deleteById(id);
    }
}
