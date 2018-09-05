package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Date;


@Controller
public class MovieController {

    @Autowired
    MoviesService moviesService;

    @RequestMapping("/movie")
    public ModelAndView movie(@RequestParam(value = "movieID", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("movie");
        try {
            mav.addObject("chosenMovie",moviesService.getMovieById(id));
        }
        catch(Exception e){};
        return mav;
    }
}