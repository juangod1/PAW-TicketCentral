package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.FoodDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;

public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodDao foodDao;
}
