package next.school.cesar.desafiospring.dao;

import next.school.cesar.desafiospring.entities.Vehicle;
import next.school.cesar.desafiospring.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("VehicleDao")
public class VehicleDao {
    @Autowired
    private VehicleRepository vehicleRepository;



    public void include(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(long id){
        Optional<Vehicle> res = vehicleRepository.findById(id);

        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }

    }

    public void update(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void delete(Vehicle vehicle){
        vehicleRepository.delete(vehicle);
    }

}
