package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setName("Sampo Cap Bambang");
        this.product.setQuantity(100);
    }

    // Positive scenario
    @Test
    void testSetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getQuantity());
    }

    // Negative scenario
    @Test
    void testSetProductIdNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setId(null));
        assertThrows(IllegalArgumentException.class, () -> product.setId(""));
        System.out.println(product.getId());
    }

    @Test
    void testSetProductNameNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setName(null));
        assertThrows(IllegalArgumentException.class, () -> product.setName(""));
        System.out.println(product.getName());
    }

    @Test
    void testSetProductQuantityNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-1));
        System.out.println(product.getQuantity());
    }
}
