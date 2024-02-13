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
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl service;

    @Mock
    ProductRepository repository;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(repository.create(product)).thenReturn(product);
        Product createdProduct = service.create(product);
        verify(repository, times(1)).create(product);
        assertEquals(product, createdProduct);
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        String productId = "1";
        when(repository.findById(productId)).thenReturn(product);
        Product foundProduct = service.findById(productId);
        verify(repository, times(1)).findById(productId);
        assertEquals(product, foundProduct);
    }

    @Test
    public void testEditProduct() {
        Product product = new Product();
        when(repository.edit(product)).thenReturn(product);
        Product editedProduct = service.edit(product);
        verify(repository, times(1)).edit(product);
        assertEquals(product, editedProduct);
    }

    @Test
    public void testFindAllProductsWhenNoneExist() {
        when(repository.findAll()).thenReturn(Collections.emptyIterator());
        List<Product> products = service.findAll();
        verify(repository, times(1)).findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindAllProductsReturnsCorrectProducts() {
        Product product1 = new Product();
        product1.setProductId("1");
        Product product2 = new Product();
        product2.setProductId("2");
        List<Product> allProducts = Arrays.asList(product1, product2);
        when(repository.findAll()).thenReturn(allProducts.iterator());
        List<Product> products = service.findAll();
        verify(repository, times(1)).findAll();
        assertEquals(2, products.size());
        assertEquals("1", products.get(0).getProductId());
        assertEquals("2", products.get(1).getProductId());
    }

    @Test
    public void testDeleteProductById() {
        String productId = "1";
        service.deleteById(productId);
        verify(repository, times(1)).deleteById(productId);
    }
}