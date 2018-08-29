package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.models.Food;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TheatreServiceImpl implements FoodService {

    @Autowired
    TheatreDao theatreDao;

    @Override
    public List<Food> getFood() {
        return null;
    }
}
