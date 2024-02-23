package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    @Test
    void testCarId() {
        Car car = new Car();
        car.setId("123");
        assertEquals("123", car.getId());
    }

    @Test
    void testCarName() {
        Car car = new Car();
        car.setName("Test Car");
        assertEquals("Test Car", car.getName());
    }

    @Test
    void testCarColor() {
        Car car = new Car();
        car.setColor("Red");
        assertEquals("Red", car.getColor());
    }

    @Test
    void testCarQuantity() {
        Car car = new Car();
        car.setQuantity(5);
        assertEquals(5, car.getQuantity());
    }
}