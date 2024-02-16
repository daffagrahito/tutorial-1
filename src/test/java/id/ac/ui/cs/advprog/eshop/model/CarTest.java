package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    @Test
    public void testCarId() {
        Car car = new Car();
        car.setCarId("123");
        assertEquals("123", car.getCarId());
    }

    @Test
    public void testCarName() {
        Car car = new Car();
        car.setCarName("Test Car");
        assertEquals("Test Car", car.getCarName());
    }

    @Test
    public void testCarColor() {
        Car car = new Car();
        car.setCarColor("Red");
        assertEquals("Red", car.getCarColor());
    }

    @Test
    public void testCarQuantity() {
        Car car = new Car();
        car.setCarQuantity(5);
        assertEquals(5, car.getCarQuantity());
    }
}