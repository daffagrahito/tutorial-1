package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        this.productId = generateUniqueProductId();
    }

    private String generateUniqueProductId() {
        return UUID.randomUUID().toString();
    }
}
