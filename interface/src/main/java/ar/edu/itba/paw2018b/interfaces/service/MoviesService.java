package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;

import java.util.List;

public interface MoviesService {

    List<Movie> getPremieres();

    List<Movie> getPremieresByTheatre(String theatreName);

    void setUpMovies();

    List<Movie> getMovies();

    List<Movie> getNonPremieres();

    List<Movie> getMoviesByTheatre(String theatreName);

    Movie getMovieById(int id);
}
