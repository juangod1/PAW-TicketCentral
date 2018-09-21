package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Movie;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MoviesDao{

    List<Movie> getAll();

    List<Movie> getPremieres();

    List<Movie> getNonPremieres();

    List<Movie> getPremieresByTheatre(String theatre);

    List<Movie> getMoviesByTheatre(String theatre);

    Optional<Movie> findMovieByTitle(String name);

    Optional<Movie> findMovieById(long id);

    Movie create(String name, float rating, Date releaseDate, int runtime, String genres, byte[] img);

    int delete(long id);

    List<Movie> getRecommendedMoviesForUser(String dni);
}
