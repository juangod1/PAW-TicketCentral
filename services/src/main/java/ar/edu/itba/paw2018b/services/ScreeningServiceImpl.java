package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;

public class ScreeningServiceImpl implements FoodService {

    @Autowired
    ScreeningDao screeningDao;
}
