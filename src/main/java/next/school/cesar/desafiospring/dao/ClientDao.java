package next.school.cesar.desafiospring.dao;


import next.school.cesar.desafiospring.entities.Client;
import next.school.cesar.desafiospring.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientDao {

    @Autowired
    private ClientRepository clientRepository;

    public void incluir(Client client){
        clientRepository.save(client);
    }

}
