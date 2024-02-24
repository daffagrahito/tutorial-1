package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

class CarRepositoryTest {

    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
        car = new Car();
        car.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setName("Test Car");
        car.setColor("Red");
        car.setQuantity(5);
        carRepository.create(car);
    }

    @Test
    void testCreate() {
        Car result = carRepository.create(car);
        assertEquals(car, result);
    }

    @Test
    void testFindAll() {
        carRepository.create(car);
        Iterator<Car> result = carRepository.findAll();
        assertTrue(result.hasNext());
        assertEquals(car, result.next());
    }

    @Test
    void testFindById() {
        carRepository.create(car);
        Car result = carRepository.findById(car.getId());
        assertEquals(car, result);
    }

    @Test
    void testEdit() {
        carRepository.create(car);
        Car updatedCar = new Car();
        updatedCar.setName("Updated Car");
        updatedCar.setColor("Blue");
        updatedCar.setQuantity(10);
        Car result = carRepository.edit(car.getId(), updatedCar);
        assertEquals(updatedCar.getName(), result.getName());
        assertEquals(updatedCar.getColor(), result.getColor());
        assertEquals(updatedCar.getQuantity(), result.getQuantity());
    }

    @Test
    void testEditWithNonExistingId() {
        Car updatedCar = new Car();
        updatedCar.setName("Updated Car");
        updatedCar.setColor("Blue");
        updatedCar.setQuantity(10);
        Car result = carRepository.edit("non existing id", updatedCar);
        assertNull(result);
    }

    @Test
    void testDelete() {
        carRepository.create(car);
        carRepository.deleteById(car.getId());
        assertNull(carRepository.findById(car.getId()));
    }

    @Test
    void testCreateWithNullId() {
        Car car = new Car();
        car.setName("Test Car");
        car.setColor("Red");
        car.setQuantity(5);
        Car result = carRepository.create(car);
        assertNotNull(result.getId());
    }

    @Test
    void testCreateWithNonNullId() {
        Car car = new Car();
        car.setId("eb218e9c-2d69-460f-0727-11an1993rrd6");
        car.setName("Test Car");
        car.setColor("Red");
        car.setQuantity(5);
        Car result = carRepository.create(car);
        assertEquals("eb218e9c-2d69-460f-0727-11an1993rrd6", result.getId());
    }

    @Test
    void testFindByIdExistingId() {
        Car result = carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(car, result);
    }

    @Test
    void testFindByIdNonExistingId() {
        Car result = carRepository.findById("Unknown ID");
        assertNull(result);
    }
}