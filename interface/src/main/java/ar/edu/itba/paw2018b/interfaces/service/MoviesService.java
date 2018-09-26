package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoviesService {

    List<Movie> getPremieres() throws NotFoundException;

    List<Movie> getPremieresByTheatre(String theatreName) throws NotFoundException;

    List<Movie> getMovies() throws NotFoundException;

    List<Movie> getNonPremieres() throws NotFoundException;

    List<Movie> getMoviesByTheatre(String theatreName) throws NotFoundException;

    Movie getMovieById(int id) throws NotFoundException;

    List<Movie> getRecommendedMoviesForUser(String dni) throws NotFoundException;

    @Transactional
    @Scheduled(cron = "0 0 5 * * Tue")
    void deactivateMovie();
}
