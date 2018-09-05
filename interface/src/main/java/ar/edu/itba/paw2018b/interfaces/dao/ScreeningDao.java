package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;

import java.sql.Timestamp;
import java.util.List;

public interface ScreeningDao {

    List<Screening> getAll();

    List<Screening> getById(int id);

    List<Screening> getByMovie(Movie m);

    List<Screening> getByTheatre(Theatre t);

    List<Screening> getByMovieAndTheatre(long movie, String theatre);

    Screening create(String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability);

    void delete(String id);

}
