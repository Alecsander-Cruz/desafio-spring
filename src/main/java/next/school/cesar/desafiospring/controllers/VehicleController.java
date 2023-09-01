package next.school.cesar.desafiospring.controllers;

import next.school.cesar.desafiospring.entities.Vehicle;
import next.school.cesar.desafiospring.mediators.VehicleMediator;
import next.school.cesar.desafiospring.response.EntityRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleMediator vehicleMediator;

    @PostMapping
    public ResponseEntity<EntityRegisterResponse<Vehicle>> include(@RequestBody Vehicle vehicle){
        String res = vehicleMediator.include(vehicle);
        if(res != null){
            return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                    new EntityRegisterResponse<Vehicle>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                    HttpStatus.OK);
        }

        return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                new EntityRegisterResponse<Vehicle>(vehicle),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}/clients")
    public ResponseEntity<EntityRegisterResponse<Vehicle>> update(@PathVariable(value = "id") long id, @RequestBody Vehicle vehicle){
        Vehicle vehicleToBeUpdate = vehicleMediator.getVehicleById(id);

        if(vehicleToBeUpdate == null){
            return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                    new EntityRegisterResponse<Vehicle>(EntityRegisterResponse.STATUS_ENTIDADE_NAO_ENCONTRADA,
                            "Veículo não encontrado!"),
                    HttpStatus.NO_CONTENT
            );
        }

        if(vehicle.getBrand() == null){
            vehicleToBeUpdate.setBrand(vehicleToBeUpdate.getBrand());
        }
        else{
            vehicleToBeUpdate.setBrand(vehicle.getBrand());
        }

        if(vehicle.getModel() == null){
            vehicleToBeUpdate.setModel(vehicleToBeUpdate.getModel());
        }
        else{
            vehicleToBeUpdate.setModel(vehicle.getModel());
        }

        if(vehicle.getYear() == 0){
            vehicleToBeUpdate.setYear(vehicleToBeUpdate.getYear());
        }
        else{
            vehicleToBeUpdate.setYear(vehicle.getYear());
        }
        vehicleToBeUpdate.setClient(vehicle.getClient());

        String res = vehicleMediator.update(vehicleToBeUpdate);

        if(res == null){
            return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                    new EntityRegisterResponse<Vehicle>(vehicleToBeUpdate),
                    HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                    new EntityRegisterResponse<Vehicle>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                    HttpStatus.OK
            );
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityRegisterResponse<Vehicle>> deleteVehicleById(@PathVariable(value = "id") long id){
        Vehicle res = vehicleMediator.getVehicleById(id);
        if(res == null){
            return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                    new EntityRegisterResponse<Vehicle>(
                            EntityRegisterResponse.STATUS_ENTIDADE_NAO_ENCONTRADA,
                            "Veículo não encontrado!"
                    ),
                    HttpStatus.OK
            );
        }

        vehicleMediator.delete(res);
        return new ResponseEntity<EntityRegisterResponse<Vehicle>>(
                new EntityRegisterResponse<>(res),
                HttpStatus.OK
        );
    }
}
