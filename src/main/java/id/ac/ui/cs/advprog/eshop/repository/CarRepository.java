package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class CarRepository {
    static int id = 0;

    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getId().equals(id)) {
                // Update the existing car with the new info
                car.setName(updatedCar.getName());
                car.setColor(updatedCar.getColor());
                car.setQuantity(updatedCar.getQuantity());
                return car;
            }
        }
        return null; // Handle the case where the car isn't found
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getId().equals(id));
    }
}
