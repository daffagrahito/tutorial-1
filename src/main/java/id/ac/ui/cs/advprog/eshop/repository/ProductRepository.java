package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements IRepository<Product> {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product edit(String id, Product updatedProduct) {
        for (Product product : productData) {
            if (product.getId().equals(id)) {
                // Update the existing product with the new info
                product.setName(updatedProduct.getName());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }
        return null; // Product not found
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteById(String productId) {
        productData.removeIf(product -> product.getId().equals(productId));
    }
}
