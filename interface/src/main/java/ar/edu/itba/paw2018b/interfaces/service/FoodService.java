package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

import java.util.List;

public interface FoodService {
    List<Food> getFood() throws NotFoundException;

    Food getFoodById(int id) throws NotFoundException;
}
