package ar.edu.itba.paw2018b.interfaces;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;

import java.sql.Timestamp;
import java.util.List;

public interface ScreeningDao {

    List<Screening> getAll();

    List<Screening> getByMovie(Movie m);

    List<Screening> getByTheatre(Theatre t);

    Screening insert(String showroom, String movie, Timestamp time, String format, String language, String theatre);

    void delete(String id);

}
