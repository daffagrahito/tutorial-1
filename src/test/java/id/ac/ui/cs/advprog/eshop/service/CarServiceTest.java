package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        when(carRepository.create(car)).thenReturn(car);
        Car result = carService.create(car);
        verify(carRepository, times(1)).create(car);
        assertEquals(car, result);
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        Car car2 = new Car();
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2).iterator());
        List<Car> result = carService.findAll();
        verify(carRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        String carId = "123";
        when(carRepository.update(carId, car)).thenReturn(car);
        carService.update(carId, car);
        verify(carRepository, times(1)).update(carId, car);
    }

    @Test
    void testFindById() {
        Car car = new Car();
        String carId = "123";
        when(carRepository.findById(carId)).thenReturn(car);
        Car result = carService.findById(carId);
        verify(carRepository, times(1)).findById(carId);
        assertEquals(car, result);
    }

    @Test
    void testDeleteCarById() {
        String carId = "123";
        doNothing().when(carRepository).delete(carId);
        carService.deleteCarById(carId);
        verify(carRepository, times(1)).delete(carId);
    }
}