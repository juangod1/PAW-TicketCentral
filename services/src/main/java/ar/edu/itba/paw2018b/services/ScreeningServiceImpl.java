package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return screeningDao.getByMovieAndTheatre(movie.getId(), theatre.getName());
    }

    @Override
    public Screening getScreeningById(int id) {
        return screeningDao.getById(id).get(0);
    }

    @Override
    public List<Seat> getSeatsByScreening(Screening screening) {
        String format = screening.getFormat();
        String[] rows = format.split("\n");
        ArrayList<Seat> ret = new ArrayList<>();
        for(int i=0; i<rows.length; i++)
        {
            String row = rows[i];
            for(int j=0; j<row.length(); j++)
            {
                char c = row.charAt(j);
                if(c=='1')
                {
                    ret.add(new Seat(j,i,false));
                }
                if(c=='0')
                {
                    ret.add(new Seat(j,i,true));
                }
            }


        }
        return ret;
    }

    @Override
    public List<String> getHoursByScreenings(List<Screening> screenings) {
        List<String> ret = new ArrayList<>();
        for(Screening screening : screenings)
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
    public List<LocalDate> getDaysByScreenings(List<Screening> screenings) {
        List<LocalDate> ret = new ArrayList<>();
        for(Screening screening : screenings)
        {
            LocalDate date = screenings.get(0).getTime().toLocalDateTime().toLocalDate();
            ret.add(date);
        }
        return ret;
    }
}
