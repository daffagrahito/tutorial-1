package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private String id;
    private String name;
    private int quantity;

    public Product() {
        this.id = generateUniqueProductId();
    }

    private String generateUniqueProductId() {
        return UUID.randomUUID().toString();
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID tidak boleh null atau empty");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Product tidak boleh null atau empty");
        }
        this.name = name;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity Product tidak boleh negatif");
        }
        this.quantity = quantity;
    }
}
