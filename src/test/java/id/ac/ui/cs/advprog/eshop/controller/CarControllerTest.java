package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.GenericService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private GenericService<Car> carService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
    }

    @SuppressWarnings("null")
    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertEquals("CreateCar", viewName);
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        String viewName = carController.createCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carService).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carService.findAll()).thenReturn(cars);

        String viewName = carController.carListPage(model);
        assertEquals("CarList", viewName);
        verify(model).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPage() {
        String carId = "someCarId";
        Car car = new Car();
        car.setCarId(carId);
        when(carService.findById(carId)).thenReturn(car);

        String viewName = carController.editCarPage(carId, model);
        assertEquals("EditCar", viewName);
        verify(model).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("someCarId");
        String viewName = carController.editCarPost(car, model);
        assertEquals("redirect:/car/listCar", viewName);
        verify(carService).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        String carId = "someCarId";
        String viewName = carController.deleteCar(carId);
        assertEquals("redirect:/car/listCar", viewName);
        verify(carService).delete(carId);
    }
}
