package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.MoviesDao;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MoviesService {

    @Autowired
    MoviesDao moviesDao;

    @Override
    public List<Movie> getPremieres() {
        return moviesDao.getPremieres();
    }

    @Override
    public List<Movie> getPremieresByTheatre(Theatre theatre) {
        return moviesDao.getPremieresByTheatre(theatre.getName());
    }

    @Override
    public List<Movie> getMovies() {
        moviesDao.setUpMovies();
        return moviesDao.getAll();
    }

    @Override
    public List<Movie> getMoviesByTheatre(Theatre theatre) {
        return moviesDao.getMoviesByTheatre(theatre.getName());
    }

    @Override
    public Movie getMovieById(int id) {
        return moviesDao.findMovieById(id).get();
    }
}
