package next.school.cesar.desafiospring.controllers;

import next.school.cesar.desafiospring.entities.Client;
import next.school.cesar.desafiospring.mediators.ClientMediator;
import next.school.cesar.desafiospring.response.EntityRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientMediator clientMediator;


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<List<Client>>(clientMediator.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") long id){
        Client res = clientMediator.getClientById(id);
        if(res == null){
            return new ResponseEntity<Client>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityRegisterResponse<Client>> include(@RequestBody  Client client){
        String res = clientMediator.include(client);
        if(res == null){
            return new ResponseEntity<EntityRegisterResponse<Client>>(
                    new EntityRegisterResponse<Client>(client),
                    HttpStatus.CREATED
            );
        }
        else{
            return new ResponseEntity<EntityRegisterResponse<Client>>(
                    new EntityRegisterResponse<Client>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                    HttpStatus.OK
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityRegisterResponse<Client>> update(@RequestBody Client client, @PathVariable(value = "id") long id){
        Client clientToBeUpdated = clientMediator.getClientById(id);
        if(clientToBeUpdated == null){
            return new ResponseEntity<EntityRegisterResponse<Client>>(
                    new EntityRegisterResponse<Client>(EntityRegisterResponse.STATUS_ENTIDADE_NAO_ENCONTRADA, "Cliente não encontrado!"),
                    HttpStatus.NOT_FOUND
                    );
        }
        else{

            clientToBeUpdated.setName(client.getName());
            clientToBeUpdated.setAge(client.getAge());
            clientToBeUpdated.setDependents(client.getDependents());
            clientToBeUpdated.setIncome(client.getIncome());
            clientToBeUpdated.setMarital_status(client.getMarital_status());

            String res = clientMediator.update(clientToBeUpdated);
            if(res == null){
                return new ResponseEntity<EntityRegisterResponse<Client>>(
                        new EntityRegisterResponse<Client>(clientToBeUpdated),
                        HttpStatus.OK
                );
            }
            else{
                return new ResponseEntity<EntityRegisterResponse<Client>>(
                        new EntityRegisterResponse<Client>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                        HttpStatus.OK
                );
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable(value = "id") long id){
        Client res = clientMediator.getClientById(id);
        if(res == null){
            return new ResponseEntity<Client>(res, HttpStatus.NOT_FOUND);
        }
        clientMediator.delete(res);
        return new ResponseEntity<Client>(res, HttpStatus.OK);
    }

}
