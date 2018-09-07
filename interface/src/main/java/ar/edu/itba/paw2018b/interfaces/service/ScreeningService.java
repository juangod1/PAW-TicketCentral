package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;

public interface ScreeningService {

    List<Screening> getScreeningByMovie(Movie movie);
    List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie);
    Screening getScreeningById(int id);
    List<String> getHoursByScreenings(List<Screening> screenings);
    List<String> getDaysByScreenings(List<Screening> screenings);

    ///List<Day> getDaysByScreening(List<Screening> screenings);
    ///List<Hour> getHOURSByScreening(List<Screening> screenings);
    //List<Seat> getBoothByScreening(Screening screening);
}
