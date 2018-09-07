package ar.edu.itba.paw2018b.controller;


import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class ScreeningController {
    @Autowired
    ScreeningService screeningService;

    @Autowired
    MoviesService moviesService;

    @Autowired
    TheatreService theatreService;

    @RequestMapping(value = "/json/screening/getScreeningsByMovie/{movieId}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Screening>> getScreeningByMovie(@PathVariable int movieId)
    {
        Movie movie = moviesService.getMovieById(movieId);
        List<Screening> list = screeningService.getScreeningByMovie(movie);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/screening/getScreeningsByTheatreMovie/{theatreName}/{movieId}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Screening>> getScreeningByTheatreMovie( @PathVariable String theatreName, @PathVariable int movieId)
    {
        Movie movie = moviesService.getMovieById(movieId);
        Theatre theatre = theatreService.getTheatreByName(theatreName);
        List<Screening> list = screeningService.getScreeningByTheatreAndMovie(theatre,movie);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}

