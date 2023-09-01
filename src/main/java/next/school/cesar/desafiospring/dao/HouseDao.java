package next.school.cesar.desafiospring.dao;

import next.school.cesar.desafiospring.entities.House;
import next.school.cesar.desafiospring.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("HouseDao")
public class HouseDao {

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getAllHouses(){
        return houseRepository.findAll();
    }

    public House getHouseById(long id){
        Optional<House> res = houseRepository.findById(id);

        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }

    }

    public void include(House house){
        houseRepository.save(house);
    }


    public void update(House house){
        houseRepository.save(house);
    }

    public void delete(House house){
        houseRepository.delete(house);
    }

}
