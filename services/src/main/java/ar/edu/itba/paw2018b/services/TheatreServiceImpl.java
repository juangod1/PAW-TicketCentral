package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    TheatreDao theatreDao;


    @Override
    public List<Theatre> getTheatres() {
        return theatreDao.getAll();
    }

    @Override
    public List<Theatre> getTheatresByScreening(Collection<Screening> screenings) {
        List<Theatre> ret = new ArrayList<>();
        List<String> theatreNames = new ArrayList<>();
        for(Screening screening : screenings)
        {
            if(!theatreNames.contains(screening.getTheatre()))
            {
                theatreNames.add(screening.getTheatre());
            }
        }
        for(String name : theatreNames)
        {
            Optional<Theatre> theatre =theatreDao.getTheatreByName(name);
            if(theatre.isPresent())
            {
                ret.add(theatre.get());
            }
        }
        return ret;
    }

    @Override
    public Theatre getTheatreByName(String theatreName) {
        return theatreDao.getTheatreByName(theatreName).get();
    }
}
