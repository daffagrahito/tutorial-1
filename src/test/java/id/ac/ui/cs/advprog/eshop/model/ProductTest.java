package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    // Positive scenario
    @Test
    void testSetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    // Negative scenario
    @Test
    public void testSetProductIdNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setProductId(null));
        assertThrows(IllegalArgumentException.class, () -> product.setProductId(""));
        System.out.println(product.getProductId());
    }

    @Test
    public void testSetProductNameNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setProductName(null));
        assertThrows(IllegalArgumentException.class, () -> product.setProductName(""));
        System.out.println(product.getProductName());
    }

    @Test
    public void testSetProductQuantityNegativeScenario() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.setProductQuantity(-1));
        System.out.println(product.getProductQuantity());
    }
}
