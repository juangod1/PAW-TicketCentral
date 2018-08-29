package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Screening> getScreeningByMovie(Movie movie) {
        return null;
    }

    @Override
    public List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie) {
        return null;
    }

    @Override
    public Screening getScreeningById(String id) {
        return null;
    }
}
