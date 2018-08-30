package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Screening> getScreeningByMovie(Movie movie) {
        return screeningDao.getByMovie(movie);
    }

    @Override
    public List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie) {
        return null;
    }

    @Override
    public Screening getScreeningById(String id) {
        return null;
    }

    @Override
    public List<Booth> getBoothsByScreening(Screening screening) {
        return null;
    }

    @Override
    public List<Timestamp> getHoursByScreenings(List<Screening> screenings) {
        return null;
    }

    @Override
    public List<Date> getDaysByScreenings(List<Screening> screenings) {
        return null;
    }
}
