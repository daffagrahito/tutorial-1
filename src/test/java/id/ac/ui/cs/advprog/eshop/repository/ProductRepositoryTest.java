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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdPositive() {
        // Arrange
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Product 1");
        product1.setQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Assert
        assertNotNull(result);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getId());
    }

    @Test
    void testFindByIdNegative() {
        // Arrange
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Product 1");
        product1.setQuantity(10);

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
        product1.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setName("Product 1");
        product1.setQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        updatedProduct.setName("Updated Product 1");
        updatedProduct.setQuantity(15);

        // Act
        Product result = productRepository.edit(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Product 1", result.getName());
        assertEquals(15, result.getQuantity());
    }

    @Test
    void testEditNegative() {
        // Arrange
        Product product1 = new Product();
        product1.setId("b5ac78c5-c3d2-47e5-bdee-67a9e9c9981b");
        product1.setName("Product 1");
        product1.setQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        updatedProduct.setName("Updated Product 3");
        updatedProduct.setQuantity(30);

        // Act
        Product result = productRepository.edit(updatedProduct);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteByIdPositive() {
        // Arrange
        Product product1 = new Product();
        product1.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setName("Product 1");
        product1.setQuantity(10);

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
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Product 1");
        product1.setQuantity(10);

        ProductRepository productRepository = new ProductRepository();
        productRepository.create(product1);

        // Act
        productRepository.deleteById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        // Assert
        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(result);
    }
}
