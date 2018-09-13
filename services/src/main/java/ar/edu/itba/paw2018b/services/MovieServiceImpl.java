package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.MoviesDao;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MoviesService {

    @Autowired
    MoviesDao moviesDao;

    @Override
    public List<Movie> getPremieres() {
        return moviesDao.getPremieres();
    }

    @Override
    public List<Movie> getPremieresByTheatre(String theatreName) {
        List<Movie> movieList = moviesDao.getPremieresByTheatre(theatreName);
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public void setUpMovies(){
        moviesDao.setUpMovies();
    }
    @Override
    public List<Movie> getMovies() {
        List<Movie> movieList =moviesDao.getAll();
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getNonPremieres(){
        List<Movie> movieList =moviesDao.getNonPremieres();;
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByTheatre(String theatreName) {
        List<Movie> movieList =moviesDao.getMoviesByTheatre(theatreName);
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Optional<Movie> movie = moviesDao.findMovieById(id);
        return movie.orElseThrow(() ->new NotFoundException("No se han encontrado peliculas!"));
    }
}
