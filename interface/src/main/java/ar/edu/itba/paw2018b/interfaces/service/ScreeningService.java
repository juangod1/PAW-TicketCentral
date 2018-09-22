package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface ScreeningService {

    List<Screening> getScreeningByMovie(int movieId) throws NotFoundException;
    List<Screening> getScreeningByTheatreAndMovie(String theatreName, int movieId) throws NotFoundException;
    Screening getScreeningById(int id) throws NotFoundException;
    List<String> getHoursByScreenings(List<Integer> screeningIds);
    List<String> getDaysByScreenings(List<Integer> screeningIds);
    List<String> getTheatresByScreening(Collection<Integer> screeningIds);
}
