package com.example.cafeapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name") // Bu, fiziksel sütun adını doğru şekilde belirtir
    private String itemName;

    @Column(name = "item_price") // Bu da "item_price" fiziksel sütununu doğru şekilde belirtir
    private String itemPrice;

    public ShoppingCart() {
    }

    public ShoppingCart(Long userId, Long itemId, String itemName, String itemPrice) {
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    // Getter ve Setter metodları

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
