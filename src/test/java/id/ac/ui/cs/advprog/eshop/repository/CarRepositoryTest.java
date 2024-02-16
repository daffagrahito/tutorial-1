package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    public void setUp() {
        carRepository = new CarRepository();
        car = new Car();
        car.setCarId("123");
        car.setCarName("Test Car");
        car.setCarColor("Red");
        car.setCarQuantity(5);
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
        Car result = carRepository.findById(car.getCarId());
        assertEquals(car, result);
    }

    @Test
    void testUpdate() {
        carRepository.create(car);
        Car updatedCar = new Car();
        updatedCar.setCarName("Updated Car");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(10);
        Car result = carRepository.update(car.getCarId(), updatedCar);
        assertEquals(updatedCar.getCarName(), result.getCarName());
        assertEquals(updatedCar.getCarColor(), result.getCarColor());
        assertEquals(updatedCar.getCarQuantity(), result.getCarQuantity());
    }

    @Test
    void testDelete() {
        carRepository.create(car);
        carRepository.delete(car.getCarId());
        assertNull(carRepository.findById(car.getCarId()));
    }

    @Test
    void testUpdateWithNonExistingId() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Updated Car");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(10);
        Car result = carRepository.update("non existing id", updatedCar);
        assertNull(result);
    }

    @Test
    public void testCreateWithNullId() {
        Car car = new Car();
        car.setCarName("Test Car");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        Car result = carRepository.create(car);
        assertNotNull(result.getCarId());
    }

    @Test
    public void testCreateWithNonNullId() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Test Car");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        Car result = carRepository.create(car);
        assertEquals("123", result.getCarId());
    }

    @Test
    public void testFindByIdExistingId() {
        Car result = carRepository.findById("123");
        assertEquals(car, result);
    }

    @Test
    public void testFindByIdNonExistingId() {
        Car result = carRepository.findById("non-existing-id");
        assertNull(result);
    }
}