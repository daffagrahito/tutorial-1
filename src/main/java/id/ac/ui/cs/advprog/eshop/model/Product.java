package id.ac.ui.cs.advprog.eshop.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

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

    public void setProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID tidak boleh null atau empty");
        }
        this.productId = productId;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Product tidak boleh null atau empty");
        }
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        if (productQuantity < 0) {
            throw new IllegalArgumentException("Quantity Product tidak boleh negatif");
        }
        this.productQuantity = productQuantity;
    }
}
