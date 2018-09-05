package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;

import java.util.List;

public interface MoviesService {

    List<Movie> getPremieres();

    List<Movie> getPremieresByTheatre(Theatre theatre);

    List<Movie> getMovies();

    List<Movie> getMoviesByTheatre(Theatre theatre);

    Movie getMovieById(int id);
}
