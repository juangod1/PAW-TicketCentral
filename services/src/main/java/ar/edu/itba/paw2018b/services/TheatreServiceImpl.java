package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    TheatreDao theatreDao;


    @Override
    public List<Theatre> getTheatres() {
        return theatreDao.getAll();
    }

    @Override
    public List<Theatre> getTheatresByScreening(List<Screening> screenings) {
        return null;
    }

    @Override
    public Theatre getTheatreByName(String theatreName) {
        List<Theatre> list = getTheatres();
        for(Theatre theatre : list)
        {
            if(theatre.getName().equals(theatreName))
            {
                return theatre;
            }
        }
        return null;
    }
}
