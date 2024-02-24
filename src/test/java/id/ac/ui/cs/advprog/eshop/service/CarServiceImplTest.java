package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carServices;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarName("Test Car");
        when(carRepository.create(car)).thenReturn(car);
        
        Car createdCar = carServices.create(car);
        
        assertEquals("Test Car", createdCar.getCarName());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarName("Car 1");
        Car car2 = new Car();
        car2.setCarName("Car 2");
        
        List<Car> carList = Arrays.asList(car1, car2);
        when(carRepository.findAll()).thenReturn(carList.iterator());
        
        List<Car> allCars = carServices.findAll();
        
        assertEquals(2, allCars.size());
        assertTrue(allCars.contains(car1) && allCars.contains(car2));
    }
    

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarName("Original Car");
        
        when(carRepository.update(car.getCarId(),car)).thenReturn(car);
        
        Car updatedCar = carServices.update(car.getCarId(),car);
        
        assertEquals("Original Car", updatedCar.getCarName());
        verify(carRepository, times(1)).update(car.getCarId(),car);
    }

    @Test
    void testDelete() {
        String carId = "someCarId";
        
        doNothing().when(carRepository).delete(carId);
        
        carServices.delete(carId);
        
        verify(carRepository, times(1)).delete(carId);
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Test Car");

        when(carRepository.findById("123")).thenReturn(car);

        Car foundCar = carServices.findById("123");

        assertEquals("Test Car", foundCar.getCarName());
        assertEquals("123", foundCar.getCarId());
        verify(carRepository, times(1)).findById("123");
    }

}
