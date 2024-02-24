package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl service;

    @Mock
    ProductRepository repository;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(repository.create(product)).thenReturn(product);
        Product createdProduct = service.create(product);
        verify(repository, times(1)).create(product);
        assertEquals(product, createdProduct);
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        String productId = "1";
        when(repository.findById(productId)).thenReturn(product);
        Product foundProduct = service.findById(productId);
        verify(repository, times(1)).findById(productId);
        assertEquals(product, foundProduct);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        when(repository.edit(product.getId(), product)).thenReturn(product);
        Product editedProduct = service.edit(product.getId(), product);
        verify(repository, times(1)).edit(product.getId(), product);
        assertEquals(product, editedProduct);
    }

    @Test
    void testFindAllProductsWhenNoneExist() {
        when(repository.findAll()).thenReturn(Collections.emptyIterator());
        List<Product> products = service.findAll();
        verify(repository, times(1)).findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindAllProductsReturnsCorrectProducts() {
        Product product1 = new Product();
        product1.setId("1");
        Product product2 = new Product();
        product2.setId("2");
        List<Product> allProducts = Arrays.asList(product1, product2);
        when(repository.findAll()).thenReturn(allProducts.iterator());
        List<Product> products = service.findAll();
        verify(repository, times(1)).findAll();
        assertEquals(2, products.size());
        assertEquals("1", products.get(0).getId());
        assertEquals("2", products.get(1).getId());
    }

    @Test
    void testDeleteProductById() {
        String productId = "1";
        service.deleteById(productId);
        verify(repository, times(1)).deleteById(productId);
    }
}