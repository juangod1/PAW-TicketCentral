package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Screening> getScreeningByMovie(int movieId) {
        return screeningDao.getByMovieId(movieId);
    }

    @Override
    public List<Screening> getScreeningByTheatreAndMovie(String theatreName, int movieId){
        return screeningDao.getByMovieAndTheatre(movieId, theatreName);
    }

    @Override
    public Screening getScreeningById(int id) {

        return screeningDao.getById(id);
    }


    @Override
    public List<String> getHoursByScreenings(List<Integer> screeningIds) {
        List<Screening> dataBaseScreenings = new ArrayList<>();
        for(Integer id : screeningIds)
        {
            Screening dataBaseScreening = screeningDao.getById(id);
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
            ret.add(timeString);
        }
        return ret;
    }

    @Override
    public List<String> getDaysByScreenings(List<Integer> screeningIds) {
        List<Screening> dataBaseScreenings = new ArrayList<>();
        for(Integer id : screeningIds)
        {
            Screening dataBaseScreening = screeningDao.getById(id);
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

    @Override
    public List<String> getTheatresByScreening(Collection<Integer> screeningIds) {

        List<String> theatreNames = new ArrayList<>();

        List<Screening> screeningList = new ArrayList<>();

        for(Integer id : screeningIds)
        {
            Screening screening = screeningDao.getById(id);
            if(screening!=null)
            {
                screeningList.add(screening);
            }
        }

        for(Screening screening : screeningList)
        {
            if(!theatreNames.contains(screening.getTheatre()))
            {
                theatreNames.add(screening.getTheatre());
            }
        }
        return theatreNames;
    }
}
