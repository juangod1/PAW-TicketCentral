package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;

public interface ScreeningService {

    List<Screening> getScreeningByMovie(Movie movie);
    List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie);
    Screening getScreeningById(String id);

    ///List<Day> getDaysByScreening(List<Screening> screenings);
    ///List<Hour> getHOURSByScreening(List<Screening> screenings);
    //List<Booth> getBoothByScreening(Screening screening);
}
