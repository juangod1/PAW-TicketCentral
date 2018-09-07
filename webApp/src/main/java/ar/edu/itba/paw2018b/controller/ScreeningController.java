package ar.edu.itba.paw2018b.controller;


import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    /**
     *
     * @param screeningJson Expects a JSON string of the following format
     *                      "[
     *                          {
     *                              all attributes of screening 1
     *                          },
     *                          ...
     *                          {
     *                              all attributes of screening n
     *                          }
     *                       ]
     *                      "
     * @return
     * @throws JsonParseException If string is not formatted correctly
     * @throws IOException If string is not formatted correctly
     */
    @RequestMapping(value = "/json/screening/getHoursByScreenings", method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<String>> getHoursByScreenings(@RequestBody String screeningJson) throws JsonParseException, IOException
    {
        List<Screening> screenings = new ObjectMapper().readValue(screeningJson, new TypeReference<List<Screening>>() { });

        List<String> list = screeningService.getHoursByScreenings(screenings);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    /**
     *
     * @param screeningJson Expects a JSON string of the following format
     *                      "[
     *                          {
     *                              all attributes of screening 1
     *                          },
     *                          ...
     *                          {
     *                              all attributes of screening n
     *                          }
     *                       ]
     *                      "
     * @return
     * @throws JsonParseException If string is not formatted correctly
     * @throws IOException If string is not formatted correctly
     */
    @RequestMapping(value = "/json/screening/getDaysByScreenings", method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<String>> getDaysByScreenings(@RequestBody String screeningJson) throws JsonParseException, IOException
    {
        List<Screening> screenings = new ObjectMapper().readValue(screeningJson, new TypeReference<List<Screening>>() { });

        List<String> list = screeningService.getDaysByScreenings(screenings);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}

