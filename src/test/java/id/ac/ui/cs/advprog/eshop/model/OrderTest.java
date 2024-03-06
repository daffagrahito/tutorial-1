package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);

        Product product2 = new Product();
        product2.setId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setName("Sabun Cap Usep");
        product2.setQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testBuilder() {
        Order order = Order.builder()
                .id("1")
                .products(Arrays.asList(new Product(), new Product()))
                .orderTime(123456789L)
                .author("author")
                .status(OrderStatus.WAITING_PAYMENT.getValue())
                .build();

        assertEquals("1", order.getId());
        assertEquals(2, order.getProducts().size());
        assertEquals(123456789L, order.getOrderTime());
        assertEquals("author", order.getAuthor());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    void testConstructorWithEmptyProducts() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("1", Collections.emptyList(), 123456789L, "author"));
    }

    @Test
    void testConstructorWithNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("1", Arrays.asList(new Product()), 123456789L, "author", null));
    }

    @Test
    void testSetStatusWithInvalidStatus() {
        Order order = Order.builder()
                .id("1")
                .products(Arrays.asList(new Product(), new Product()))
                .orderTime(123456789L)
                .author("author")
                .status(OrderStatus.WAITING_PAYMENT.getValue())
                .build();

        assertThrows(IllegalArgumentException.class, () -> order.setStatus("invalid_status"));
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L,
                    "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getName());

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat",
                OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L,
                    "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus(OrderStatus.CANCELLED.getValue());
        assertEquals(OrderStatus.CANCELLED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("MEOW");
        });
    }
}
