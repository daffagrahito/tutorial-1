package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product edit(String id, Product product);

    List<Product> findAll();

    Product findById(String id);

    void deleteById(String productId);
}
