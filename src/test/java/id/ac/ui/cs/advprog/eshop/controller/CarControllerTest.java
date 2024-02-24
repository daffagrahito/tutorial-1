package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    private CarController carController;

    @Mock
    private CarService carService;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setup() {
        carController = new CarController(carService);
    }

    @Test
    void testCreateCarPage() {
        String view = carController.createCarPage(model);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
        assertEquals("CreateCar", view);
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        when(carService.create(car)).thenReturn(car);
        String view = carController.createCarPost(car);
        assertEquals("redirect:listCar", view);
    }

    @Test
    void testCarListPage() {
        List<Car> allCars = Arrays.asList(new Car());
        when(carService.findAll()).thenReturn(allCars);
        String view = carController.carListPage(model);
        verify(model, times(1)).addAttribute("cars", allCars);
        assertEquals("ListCar", view);
    }

    @Test
    void testEditCarPage() {
        Car car = new Car();
        car.setId("123");
        when(carService.findById("123")).thenReturn(car);
        String view = carController.editCarPage("123", model);
        verify(model, times(1)).addAttribute("car", car);
        assertEquals("EditCar", view);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setId("123");
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals("123", arg0);
            assertEquals(car, arg1);

            return null;
        }).when(carService).edit(anyString(), any(Car.class));

        String view = carController.editCarPost(car);
        assertEquals("redirect:listCar", view);
    }

    @Test
    void testDeleteCar() {
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals("123", arg0);

            return null;
        }).when(carService).deleteCarById(anyString());

        String view = carController.deleteCar("123");
        verify(carService, times(1)).deleteCarById("123");
        assertEquals("redirect:listCar", view);
    }
}