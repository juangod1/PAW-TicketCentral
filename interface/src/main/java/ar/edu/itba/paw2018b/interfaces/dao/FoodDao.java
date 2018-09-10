package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Food;


import java.util.List;
import java.util.Optional;

public interface FoodDao {

    Optional<Food> findById(int id);

    List<Food> getAll();

    Food create(String name, int price, int stock, byte[] image);

    void setUpFood();

    void delete(int id);

}
