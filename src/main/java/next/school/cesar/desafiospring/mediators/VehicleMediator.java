package next.school.cesar.desafiospring.mediators;

import next.school.cesar.desafiospring.dao.VehicleDao;
import next.school.cesar.desafiospring.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("VehicleMediator")
public class VehicleMediator {
    @Autowired
    private VehicleDao vehicleDao;

    public Vehicle getVehicleById(long id){
        return vehicleDao.getVehicleById(id);
    }

    public String include(Vehicle vehicle){

        String res = verifyVehicleData(vehicle);

        if(res == null){
            vehicleDao.include(vehicle);
        }

        return res;
    }

    public String update(Vehicle vehicle){

        String res = verifyVehicleData(vehicle);

        if(res == null){
            vehicleDao.update(vehicle);
        }

        return res;
    }

    public void delete(Vehicle vehicle){
        vehicleDao.delete(vehicle);
    }

    // Vehicle Specifications validation
    private String verifyVehicleData(Vehicle vehicle){
        // Brand validation
        if(vehicle.getBrand() == null || vehicle.getBrand().isEmpty()){
            return "Marca não pode ser nula ou vazia!";
        }


        if(vehicle.getModel() == null || vehicle.getModel().isEmpty()){
            return "Modelo não pode ser nulo ou vazio!";
        }


        if(vehicle.getYear() < 1886){
            return "Ano não pode ser nulo ou vazio!";
        }

        return null;
    }
}
