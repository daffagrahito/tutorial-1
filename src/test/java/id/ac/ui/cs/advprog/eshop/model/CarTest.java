package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testSetIdWithNull() {
        Car car = new Car();
        assertThrows(IllegalArgumentException.class, () -> car.setId(null));
    }

    @Test
    void testSetNameWithNull() {
        Car car = new Car();
        assertThrows(IllegalArgumentException.class, () -> car.setName(null));
    }

    @Test
    void testSetColorWithValidColor() {
        Car car = new Car();
        String color = "Blue";
        car.setColor(color);
        assertEquals(color, car.getColor());
    }

    @Test
    void testSetColorWithNull() {
        Car car = new Car();
        assertThrows(IllegalArgumentException.class, () -> car.setColor(null));
    }

    @Test
    void testSetColorWithEmptyString() {
        Car car = new Car();
        assertThrows(IllegalArgumentException.class, () -> car.setColor(""));
    }

    @Test
    void testSetQuantityWithNegative() {
        Car car = new Car();
        assertThrows(IllegalArgumentException.class, () -> car.setQuantity(-1));
    }
}