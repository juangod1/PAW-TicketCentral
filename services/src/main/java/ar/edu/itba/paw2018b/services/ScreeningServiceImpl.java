package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Screening> getScreeningByMovie(Movie movie) {
        return screeningDao.getByMovie(movie);
    }

    @Override
    public List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie) {
        return screeningDao.getByMovieAndTheatre(movie.getId(), theatre.getName());
    }

    @Override
    public Screening getScreeningById(int id) {

        List<Screening> list = screeningDao.getById(id);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }


    @Override
    public List<String> getHoursByScreenings(List<Screening> screenings) {
        List<Screening> dataBaseScreenings = new ArrayList<>();
        for(Screening screening : screenings)
        {
            Screening dataBaseScreening = screeningDao.getById(screening.getId()).get(0);
            if(dataBaseScreening!=null)
            {
                dataBaseScreenings.add(dataBaseScreening);
            }

        }
        List<String> ret = new ArrayList<>();
        for(Screening screening : dataBaseScreenings)
        {
            LocalDateTime time = screening.getTime().toLocalDateTime();
            String timeString = time.getHour()+":"+time.getMinute();
            if(!ret.contains(timeString))
            {
                ret.add(timeString);
            }
        }
        return ret;
    }

    @Override
    public List<String> getDaysByScreenings(List<Screening> screenings) {
        List<Screening> dataBaseScreenings = new ArrayList<>();
        for(Screening screening : screenings)
        {
            Screening dataBaseScreening = screeningDao.getById(screening.getId()).get(0);
            if(dataBaseScreening!=null)
            {
                dataBaseScreenings.add(dataBaseScreening);
            }

        }
        List<String> ret = new ArrayList<>();
        for(Screening screening : dataBaseScreenings)
        {
            LocalDate date = screening.getTime().toLocalDateTime().toLocalDate();
            ret.add(date.toString());
        }
        return ret;
    }
}
