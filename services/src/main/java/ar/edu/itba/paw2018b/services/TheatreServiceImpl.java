package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;

public class TheatreServiceImpl implements FoodService {

    @Autowired
    TheatreDao theatreDao;
}
