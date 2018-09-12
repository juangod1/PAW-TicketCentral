package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.FoodDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.models.Food;
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
        return foodDao.getAll();
    }

    @Override
    public Food getFoodById(int id){
        Optional<Food> food = foodDao.findById(id);
        if(food.isPresent())
            return food.get();
        return null;
    }

    @Override
    public void setUpFoods(){
        foodDao.setUpFood();
    }
}
