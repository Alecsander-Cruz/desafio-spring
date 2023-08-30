package next.school.cesar.desafiospring.controllers;

import next.school.cesar.desafiospring.entities.Client;
import next.school.cesar.desafiospring.mediators.ClientMediator;
import next.school.cesar.desafiospring.response.EntityRegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientMediator clientMediator = new ClientMediator();

    @PostMapping
    public ResponseEntity<EntityRegisterResponse<Client>> include(@RequestBody  Client client){
        String res = clientMediator.incluir(client);
        if(res == null){
            return new ResponseEntity<EntityRegisterResponse<Client>>(
                    new EntityRegisterResponse<Client>(EntityRegisterResponse.STATUS_OK, res),
                    HttpStatus.CREATED
            );
        }
        else{
            return new ResponseEntity<EntityRegisterResponse<Client>>(
                    new EntityRegisterResponse<Client>(EntityRegisterResponse.STATUS_DADO_INVALIDO, res),
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
            );
        }
    }


}
