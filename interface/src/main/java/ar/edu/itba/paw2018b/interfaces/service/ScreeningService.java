package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.Collection;
import java.util.List;

public interface ScreeningService {

    List<Screening> getScreeningByMovie(int movieId);
    List<Screening> getScreeningByTheatreAndMovie(String theatreName, int movieId);
    Screening getScreeningById(int id);
    List<String> getHoursByScreenings(List<Integer> screeningIds);
    List<String> getDaysByScreenings(List<Integer> screeningIds);
    List<String> getTheatresByScreening(Collection<Integer> screeningIds);


    ///List<Day> getDaysByScreening(List<Screening> screenings);
    ///List<Hour> getHOURSByScreening(List<Screening> screenings);
    //List<Seat> getBoothByScreening(Screening screening);
}
