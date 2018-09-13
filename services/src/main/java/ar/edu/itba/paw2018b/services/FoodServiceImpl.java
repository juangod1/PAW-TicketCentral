package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.FoodDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodDao foodDao;

    @Override
    public List<Food> getFood() {
        List<Food> foodList = foodDao.getAll();
        if(foodList.size()==0){
            throw new NotFoundException("No se ha encontrado Comida!");
        }
        return foodList;
    }

    @Override
    public Food getFoodById(int id) {
        Optional<Food> food = foodDao.findById(id);
        return food.orElseThrow(() -> new NotFoundException("No se ha encontrado Comida!"));
    }

    @Override
    public void setUpFoods(){
        foodDao.setUpFood();
    }
}
