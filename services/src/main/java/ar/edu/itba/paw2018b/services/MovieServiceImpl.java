package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.MoviesDao;
import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MoviesService {

    @Autowired
    MoviesDao moviesDao;

    @Override
    public List<Movie> getPremieres() {
        return moviesDao.getPremieres();
    }

    @Override
    public List<Movie> getPremieresByTheatre(Theatre theatre) {
        return null;
    }

    @Override
    public List<Movie> getMovies() {
        return moviesDao.getAll();
    }

    @Override
    public List<Movie> getMoviesByTheatre(Theatre theatre) {
        return null;
    }

    @Override
    public Movie getMovieById(String id) {

        return moviesDao.findMovieById(id).get();
    }
}
