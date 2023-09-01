package next.school.cesar.desafiospring.dao;


import next.school.cesar.desafiospring.entities.Client;
import next.school.cesar.desafiospring.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ClientDao")
public class ClientDao {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
            return clientRepository.findAll();
    }

    public void include(Client client){
        clientRepository.save(client);
    }

    public Client getClientById(long id){
        Optional<Client> res = clientRepository.findById(id);

        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }

    }

    public void update(Client client){
        clientRepository.save(client);
    }

    public void delete(Client client){
        clientRepository.delete(client);
    }

}
