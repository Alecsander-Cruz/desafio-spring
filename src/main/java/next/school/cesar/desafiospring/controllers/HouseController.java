package next.school.cesar.desafiospring.controllers;

import next.school.cesar.desafiospring.entities.House;
import next.school.cesar.desafiospring.mediators.HouseMediator;
import next.school.cesar.desafiospring.response.EntityRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseMediator houseMediator;

    @GetMapping
    public ResponseEntity<List<House>> getAllHouses(){
        return new ResponseEntity<List<House>>(houseMediator.getAllHouses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityRegisterResponse<House>> include(@RequestBody House house){
        String res = houseMediator.include(house);
        if(res == null){
            return new ResponseEntity<EntityRegisterResponse<House>>(
                    new EntityRegisterResponse<House>(house),
                    HttpStatus.CREATED
            );
        }
        else{
            return new ResponseEntity<EntityRegisterResponse<House>>(
                    new EntityRegisterResponse<House>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                    HttpStatus.OK
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityRegisterResponse<House>> update(@RequestBody House house, @PathVariable(value = "id") long id){
        House houseToBeUpdated = houseMediator.getHouseById(id);
        if(houseToBeUpdated == null){
            return new ResponseEntity<EntityRegisterResponse<House>>(
                    new EntityRegisterResponse<House>(EntityRegisterResponse.STATUS_ENTIDADE_NAO_ENCONTRADA, "Casa não encontrado!"),
                    HttpStatus.NO_CONTENT
            );
        }
        else{

            houseToBeUpdated.setOwnership_status(house.getOwnership_status());
            houseToBeUpdated.setLocation(house.getLocation());
            houseToBeUpdated.setZipcode(house.getZipcode());
            houseToBeUpdated.setClient(house.getClient());


            String res = houseMediator.update(houseToBeUpdated);
            if(res == null){
                return new ResponseEntity<EntityRegisterResponse<House>>(
                        new EntityRegisterResponse<House>(houseToBeUpdated),
                        HttpStatus.OK
                );
            }
            else{
                return new ResponseEntity<EntityRegisterResponse<House>>(
                        new EntityRegisterResponse<House>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                        HttpStatus.OK
                );
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<House> deleteHouseById(@PathVariable(value = "id") long id){
        House res = houseMediator.getHouseById(id);
        if(res == null){
            return new ResponseEntity<House>(res, HttpStatus.NO_CONTENT);
        }
        houseMediator.delete(res);
        return new ResponseEntity<House>(res, HttpStatus.OK);
    }

}
