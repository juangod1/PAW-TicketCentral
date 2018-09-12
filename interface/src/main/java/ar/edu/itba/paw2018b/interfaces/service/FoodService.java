package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Food;

import java.util.List;

public interface FoodService {
    List<Food> getFood();

    Food getFoodById(int id);

    void setUpFoods();

    //List<Food> getFoodByTheatre(Theatre); ???????????
}
