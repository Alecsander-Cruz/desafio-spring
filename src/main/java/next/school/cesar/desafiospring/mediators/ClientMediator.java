package next.school.cesar.desafiospring.mediators;

import next.school.cesar.desafiospring.dao.ClientDao;
import next.school.cesar.desafiospring.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("ClientMediator")
public class ClientMediator {

    @Autowired
    private ClientDao clientDao;

    public List<Client> getAllClients(){
        return clientDao.getAllClients();
    }

    public Client getClientById(long id){
        return clientDao.getClientById(id);
    }

    public String include(Client client){

        String res = verifyClientData(client);

        MaritalStatusToLowerCase(client);

        if(res == null){
            // LocalDateTime insert
            client.setCreatedAt(LocalDateTime.now());
            client.setUpdatedAt(LocalDateTime.now());

            clientDao.include(client);
        }

        return res;
    }

    public String update(Client client){

        String res = verifyClientData(client);
        MaritalStatusToLowerCase(client);

        if(res == null){
            client.setCreatedAt(client.getCreatedAt());
            client.setUpdatedAt(LocalDateTime.now());
            clientDao.update(client);
        }

        return res;
    }

    public void delete(Client client){
        clientDao.delete(client);
    }

    // Client Specifications validation
    private String verifyClientData(Client client){
        // Name validation
        if(client.getName() == null || client.getName().isEmpty()){
            return "Nome do cliente vazio!";
        }

        // Age validation
        if(client.getAge() == null){
            return "Idade não pode ser nula";
        }
        if(client.getAge() < 0){
            return "Idade menor que zero!";
        }

        // Dependents validation
        if(client.getDependents() == null){
            return "Número de dependentes não pode ser nulo";
        }
        if(client.getDependents() < 0){
            return "Número de dependentes menor que zero!";
        }

        // Income validation
        if(client.getIncome() == null){
            return "A renda não pode ser nula";
        }
        if(client.getIncome() < 0){
            return "A renda não pode ser menor que zero!";
        }

        // Marital Status validation
        if(client.getMarital_status() == null){
            return "Estado civil não encontrado!";
        }
        if(!client.getMarital_status().equalsIgnoreCase("single")
                && !client.getMarital_status().equalsIgnoreCase("married")){
            return "Estado civil diferente de 'single' ou 'married'";
        }

        return null;
    }

    // Marital Status
    private void MaritalStatusToLowerCase(Client client){
        client.setMarital_status(client.getMarital_status().toLowerCase());
    }

}
