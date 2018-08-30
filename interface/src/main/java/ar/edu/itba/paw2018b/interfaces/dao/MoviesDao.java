package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Movie;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MoviesDao{

    List<Movie> getAll();

    List<Movie> getPremieres();

    List<Movie> getPremieresByTheatre(String theatre);

    List<Movie> getMoviesByTheatre(String theatre);

    Optional<Movie> findMovieByTitle(String name);

    Optional<Movie> findMovieById(String id);

    Movie create(String id, String name, float rating, Date releaseDate, int runtime, String genres, byte[] img);

    void delete(String id);
}
