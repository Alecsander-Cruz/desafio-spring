package next.school.cesar.desafiospring.mediators;

import next.school.cesar.desafiospring.dao.ClientDao;
import next.school.cesar.desafiospring.entities.Client;

public class ClientMediator {

    private ClientDao clientDao = new ClientDao();

    public String incluir(Client client){

        // Name validation
        if(client.getName() == null){
            return "Nome do cliente vazio!";
        }

        // Age validation
//        if(client.getAge() == null){
//            return "Idade não encontrada";
//        }
        if(client.getAge() < 0){
            return "Idade menor que zero!";
        }

        if(client.getMarital_status() == null){
            return "Estado civil não encontrado!";
        }
        if(!client.getMarital_status().equals("single") && !client.getMarital_status().equals("married")){
            return "Estado civil diferente de 'single' ou 'married'";
        }

        clientDao.incluir(client);

        return null;
    }
}
