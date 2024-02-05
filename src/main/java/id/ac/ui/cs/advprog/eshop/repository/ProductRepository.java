package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product edit(Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product existingProduct = productData.get(i);
            if (existingProduct.getProductId().equals(updatedProduct.getProductId())) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null; // Product not found
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
