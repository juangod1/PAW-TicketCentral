package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
public class MovieController {

    @Autowired
    MoviesService moviesService;

    @Autowired
    TheatreService theatreService;

    @RequestMapping(value = "/json/movie/getMoviesByTheatre/{theatreName}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Movie>> getMoviesByTheatre(@PathVariable String theatreName)
    {
        List<Movie> list = moviesService.getMoviesByTheatre(theatreName);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/movie/getPremieresByTheatre/{theatreName}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Movie>> getPremieresByTheatre(@PathVariable String theatreName)
    {
        List<Movie> list = moviesService.getPremieresByTheatre(theatreName);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/movie/getPremieres", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Movie>> getPremieres()
    {
        List<Movie> list = moviesService.getPremieres();
        if(list.size()==0)
        {
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/movie/getMovies", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Movie>> getMovies()
    {
        List<Movie> list = moviesService.getMovies();
        if(list.size()==0)
        {
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}