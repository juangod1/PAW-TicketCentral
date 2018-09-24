package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ScreeningDao {

    List<Screening> getAll();

    Optional<Screening> getById(long id);

    List<Screening> getByMovie(Movie m);

    List<Screening> getByMovieId(int movieId);

    List<Screening> getByTheatre(Theatre t);

    List<Screening> getByMovieAndTheatre(long movie, String theatre);

    Screening create(String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability, int price);

    int delete(long id);

}
