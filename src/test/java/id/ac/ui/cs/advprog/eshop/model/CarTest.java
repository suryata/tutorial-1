package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("abcd1234");
        this.car.setCarName("Toyota Supra");
        this.car.setCarColor("Red");
        this.car.setCarQuantity(5);
    }

    @Test
    void testGetCarId() {
        assertEquals("abcd1234", this.car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota Supra", this.car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Red", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(5, this.car.getCarQuantity());
    }
}
