package next.school.cesar.desafiospring.mediators;

import next.school.cesar.desafiospring.dao.HouseDao;
import next.school.cesar.desafiospring.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("HouseMediator")
public class HouseMediator {

    @Autowired
    private HouseDao houseDao;

    public List<House> getAllHouses(){
        return houseDao.getAllHouses();
    }

    public House getHouseById(long id){
        return houseDao.getHouseById(id);
    }

    public String include(House house){

        String res = verifyHouseData(house);

        OwnershipStatusToLowerCase(house);

        if(res == null){
            houseDao.include(house);
        }

        return res;
    }

    public String update(House house){

        String res = verifyHouseData(house);
        OwnershipStatusToLowerCase(house);

        if(res == null){
            houseDao.update(house);
        }

        return res;
    }

    public void delete(House house){
        houseDao.delete(house);
    }

    // House Specifications validation
    private String verifyHouseData(House house){

        // ownership_satus validation
        if(house.getOwnership_status() == null || house.getOwnership_status().isEmpty()){
            return "Status da casa não pode ser nulo!";
        }
        if(!house.getOwnership_status().equalsIgnoreCase("owned")
            && !house.getOwnership_status().equalsIgnoreCase("mortgaged")){
            return "Status da casa diferente de 'owned' ou 'mortgaged'!";
        }

        // location validation
        if(house.getLocation() == null || house.getLocation().isEmpty()){
            return "Localização não pode ser nula!";
        }

        //zipcode validation
        if(house.getZipcode() == null || house.getZipcode().isEmpty()){
            return "CEP não pode ser nulo!";
        }

        return null;
    }

    private void OwnershipStatusToLowerCase(House house){
        house.setOwnership_status(house.getOwnership_status().toLowerCase());
    }
}
