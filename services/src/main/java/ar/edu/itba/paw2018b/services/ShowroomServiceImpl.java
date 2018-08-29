package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ShowroomsDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Showroom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShowroomServiceImpl implements FoodService {

    @Autowired
    ShowroomsDao showroomsDao;

    @Override
    public List<Food> getFood() {
        return null;
    }
}
