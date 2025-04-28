package com.example.cafeapp.controller;

import com.example.cafeapp.model.ShoppingCart;
import com.example.cafeapp.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-cart")
@CrossOrigin(origins = "*")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Sepete item eklemek için API
    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestBody ShoppingCart shoppingCart) {
        // Item'ı sepete eklemeden önce doğrulama yapalım
        Optional<ShoppingCart> existingItem = shoppingCartService.findItemInCart(shoppingCart.getUserId(), shoppingCart.getItemId());
        if (existingItem.isPresent()) {
            return ResponseEntity.badRequest().body("Item is already in the cart.");
        }

        shoppingCartService.addItemToCart(shoppingCart);
        return ResponseEntity.ok("Item added to cart successfully.");
    }
    // Kullanıcıya ait sepetteki item'ları getir
    @GetMapping("/getItems")
    public ResponseEntity<List<ShoppingCart>> getItemsInCart(@RequestParam Long userId) {
        List<ShoppingCart> items = shoppingCartService.getItemsInCart(userId);
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Sepette hiç item yok
        }
        return ResponseEntity.ok(items); // Sepetteki item'lar
    }


    // Sepetten bir item'ı silmek için API
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Long userId, @RequestParam Long itemId) {
        boolean success = shoppingCartService.removeItemFromCart(userId, itemId);
        if (success) {
            return ResponseEntity.ok("Item removed successfully from cart.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the cart.");
        }
    }

}
