package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Repository
public class CarRepo implements GenericRepository<Car> {
    private Map<String, Car> idToCarMap = new HashMap<>();

    @Override
    public Car create(Car car){
        car.setCarId(car.getCarId() == null ? UniqueIdGenerator.generate() : car.getCarId());
        idToCarMap.put(car.getCarId(), car);
        return car;
    }

    @Override
    public Iterator<Car> findAll(){
        return idToCarMap.values().iterator();
    }

    @Override
    public Car findById(String id) {
        return idToCarMap.get(id);
    }

    @Override
    public Car update(String id, Car updatedCar){
        if(idToCarMap.containsKey(id)){
            Car car = idToCarMap.get(id);
            car.setCarName(updatedCar.getCarName());
            car.setCarQuantity(updatedCar.getCarQuantity());
            car.setCarColor(updatedCar.getCarColor());
            return car;
        }
        return null;
    }

    @Override
    public void delete(String id){
        idToCarMap.remove(id);
    }
}