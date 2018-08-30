package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;
import sun.awt.X11.Screen;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ScreeningService {

    List<Screening> getScreeningByMovie(Movie movie);
    List<Screening> getScreeningByTheatreAndMovie(Theatre theatre, Movie movie);
    Screening getScreeningById(String id);
    List<Booth> getBoothsByScreening(Screening screening);
    List<Timestamp> getHoursByScreenings(List<Screening> screenings);
    List<Date> getDaysByScreenings(List<Screening> screenings);

    ///List<Day> getDaysByScreening(List<Screening> screenings);
    ///List<Hour> getHOURSByScreening(List<Screening> screenings);
    //List<Booth> getBoothByScreening(Screening screening);
}
