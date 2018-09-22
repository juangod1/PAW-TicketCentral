package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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
    public List<Theatre> getTheatres()  throws NotFoundException{
        List<Theatre> theatreList = theatreDao.getAll();
        if(theatreList.size()==0)
        {
            throw new NotFoundException("No se encontraron Cines!");
        }
        return theatreList;
    }

    @Override
    public Theatre getTheatreByName(String theatreName)  throws NotFoundException{
        Optional<Theatre> theatre = theatreDao.getTheatreByName(theatreName);
        return theatre.orElseThrow(() -> new NotFoundException("No se encontraron Cines!"));
    }
}
