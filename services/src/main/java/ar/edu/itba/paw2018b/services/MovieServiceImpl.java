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
    public List<Movie> getPremieres()  throws NotFoundException{
        List<Movie> premiereList = moviesDao.getPremieres();
        if(premiereList.size()==0){
            throw new NotFoundException("No  se han encontrado peliculas!");
        }
        return premiereList;
    }

    @Override
    public List<Movie> getPremieresByTheatre(String theatreName) throws NotFoundException {
        List<Movie> movieList = moviesDao.getPremieresByTheatre(theatreName);
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getMovies() throws NotFoundException {
        List<Movie> movieList =moviesDao.getAll();
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getNonPremieres() throws NotFoundException{
        List<Movie> movieList =moviesDao.getNonPremieres();;
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByTheatre(String theatreName) throws NotFoundException{
        List<Movie> movieList =moviesDao.getMoviesByTheatre(theatreName);
        if(movieList.size()==0){
            throw new NotFoundException("No se han encontrado peliculas!");
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) throws NotFoundException{
        Optional<Movie> movie = moviesDao.findMovieById(id);
        return movie.orElseThrow(() ->new NotFoundException("No se han encontrado peliculas!"));
    }

    @Override
    public List<Movie> getRecommendedMoviesForUser(String dni)  throws NotFoundException{
        List<Movie> movieList = moviesDao.getRecommendedMoviesForUser(dni);
        if(movieList.size()==0){
            throw new NotFoundException("No se encontraron peliculas!");
        }
        return movieList;
    }
}
