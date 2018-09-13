package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.models.*;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    ScreeningDao screeningDao;

    @Override
    public List<Screening> getScreeningByMovie(int movieId) {
        List<Screening> screeningList = screeningDao.getByMovieId(movieId);
        if(screeningList.size()==0)
        {
            throw new NotFoundException("No se encontraron funciones!");
        }
        return screeningList;
    }

    @Override
    public List<Screening> getScreeningByTheatreAndMovie(String theatreName, int movieId){
        List<Screening> screeningList = screeningDao.getByMovieAndTheatre(movieId, theatreName);
        if(screeningList.size()==0)
        {
            throw new NotFoundException("No se encontraron funciones!");
        }
        return screeningList;
    }

    @Override
    public Screening getScreeningById(int id) {

        Optional<Screening> screening = screeningDao.getById(id);
        return screening.orElseThrow(()  -> new NotFoundException("No se encontraron funciones!"));
    }


    @Override
    public List<String> getHoursByScreenings(List<Integer> screeningIds) {
        List<Screening> dataBaseScreenings = new ArrayList<>();
        for(Integer id : screeningIds)
        {
            Screening dataBaseScreening = getScreeningById(id);
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
            Screening dataBaseScreening = getScreeningById(id);
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
            Screening screening = getScreeningById(id);
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
