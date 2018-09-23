package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface TheatreService {

    List<Theatre> getTheatres() throws NotFoundException;

    Theatre getTheatreByName(String theatreName) throws NotFoundException;

    List<Theatre> getTheatresByMovie(int movieId);
}
