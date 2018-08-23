package ar.edu.itba.paw2018b.interfaces;

import ar.edu.itba.paw2018b.models.Movie;

import java.util.List;

public interface MoviesDao{

    List<Movie> getAll();

    List<Movie> getPremieres();

    Movie findMovieByTitle(String name);

    void insert(String id, String name, float rating, int year, int runtime, String genres, boolean premiere);

    void delete(String id);
}
