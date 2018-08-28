package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesDao{

    List<Movie> getAll();

    List<Movie> getPremieres();

    Optional<Movie> findMovieByTitle(String name);

    Optional<Movie> findMovieById(String id);

    Movie create(String id, String name, float rating, int year, int runtime, String genres, boolean premiere);

    void delete(String id);
}
