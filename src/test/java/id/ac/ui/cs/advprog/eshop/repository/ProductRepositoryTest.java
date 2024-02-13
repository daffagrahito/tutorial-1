package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdPositive() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Assert
        assertNotNull(result);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
    }

    @Test
    void testFindByIdNegative() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        Product result = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        // Assert
        assertNull(result);
    }

    @Test
    void testEditPositive() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        updatedProduct.setProductName("Updated Product 1");
        updatedProduct.setProductQuantity(15);

        // Act
        Product result = productRepository.edit(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Product 1", result.getProductName());
        assertEquals(15, result.getProductQuantity());
    }

    @Test
    void testEditNegative() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("b5ac78c5-c3d2-47e5-bdee-67a9e9c9981b");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        updatedProduct.setProductName("Updated Product 3");
        updatedProduct.setProductQuantity(30);

        // Act
        Product result = productRepository.edit(updatedProduct);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteByIdPositive() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        productRepository.deleteById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        // Assert
        Product result = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNull(result);
    }

    @Test
    void testDeleteByIdNegative() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        productRepository.deleteById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        // Assert
        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(result);
    }
}
