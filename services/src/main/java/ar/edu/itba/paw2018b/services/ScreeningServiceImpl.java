package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.models.Food;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScreeningServiceImpl implements FoodService {

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Food> getFood() {
        return null;
    }
}
