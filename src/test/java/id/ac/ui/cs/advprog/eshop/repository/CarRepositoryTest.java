package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    @InjectMocks
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        // Ini untuk men-setup kondisi awal jika diperlukan
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("abc123");
        car.setCarName("Toyota");
        car.setCarQuantity(10);
        car.setCarColor("Red");
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals("Toyota", savedCar.getCarName());
        assertEquals(10, savedCar.getCarQuantity());
        assertEquals("Red", savedCar.getCarColor());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("abc123");
        car1.setCarName("Toyota");
        car1.setCarQuantity(10);
        car1.setCarColor("Red");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("def456");
        car2.setCarName("Honda");
        car2.setCarQuantity(20);
        car2.setCarColor("Blue");
        carRepository.create(car2);

        assertEquals(car1, carRepository.findById(car1.getCarId()));
        assertEquals(car2, carRepository.findById(car2.getCarId()));
    }

    @Test
    void testEditCarPositive() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarQuantity(10);
        car.setCarColor("Red");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarId(car.getCarId()); // Same ID
        updatedCar.setCarName("Honda");
        updatedCar.setCarQuantity(20);
        updatedCar.setCarColor("Blue");

        Car result = carRepository.update(updatedCar.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("Honda", result.getCarName());
        assertEquals(20, result.getCarQuantity());
        assertEquals("Blue", result.getCarColor());
    }

    @Test
    void testEditCarNegative() {
        Car nonExistentCar = new Car();
        nonExistentCar.setCarName("Non-existent Car");
        nonExistentCar.setCarQuantity(100);
        nonExistentCar.setCarColor("Yellow");

        Car result = carRepository.update(nonExistentCar.getCarId(), nonExistentCar);

        assertNull(result);
    }

    @Test
    public void testDeleteCar() {
        Car car = new Car();
        car.setCarName("CarToDelete");
        car.setCarQuantity(10);
        car.setCarColor("Red");
        carRepository.create(car);

        carRepository.delete(car.getCarId());

        Iterator<Car> iterator = carRepository.findAll();
        boolean carExists = false;
        while(iterator.hasNext()) {
            if(iterator.next().getCarId().equals(car.getCarId())) {
                carExists = true;
                break;
            }
        }
        assertFalse(carExists);
    }

    @Test
    public void testDeleteNonExistingCar() {
        String nonExistingCarId = "nonExistingCarId";

        carRepository.delete(nonExistingCarId);

        Iterator<Car> iterator = carRepository.findAll();
        boolean carExists = false;
        while(iterator.hasNext()) {
            if(iterator.next().getCarId().equals(nonExistingCarId)) {
                carExists = true;
                break;
            }
        }
        assertFalse(carExists, "Non-existing car should not exist");
    }
}
