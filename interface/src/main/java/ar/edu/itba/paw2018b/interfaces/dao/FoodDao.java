package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Food;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface FoodDao {

    Optional<Food> findById(String id);

    List<Food> getAll();

    Food create(String id, String name, int price, int stock, Blob image);

    void delete(String id);

}
