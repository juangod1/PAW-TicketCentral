package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Food;

import java.util.List;

public interface FoodDao {

    Food findById(String id);

    List<Food> getAll();

    void insert(String id, String name, int price, int stock);

    void delete(String id);

}
