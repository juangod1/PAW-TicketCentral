package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;

import java.util.List;

public interface TheatreService {

    List<Theatre> getTheatres();
    List<Theatre> getTheatresByScreening(List<Screening> screenings);
}