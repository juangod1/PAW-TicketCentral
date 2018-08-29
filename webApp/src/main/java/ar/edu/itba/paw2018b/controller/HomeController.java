package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");

        LinkedList<Movie> movies = new LinkedList<>(); // getMovies service

        movies.add(new Movie("12","Sharknado", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("12","Sharky Sharky", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("12","Sharknado IV", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("12","69 sharknados", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("12","Shark Dog Thing", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("12","Attack Of The Sharks", 5.0f,2000,120,"Acción",false));

        mav.addObject("movies", movies);




        LinkedList<Movie> premieres = new LinkedList<>(); // getPremieres service

        premieres.add(new Movie("12","Cenicienta", 5.0f,2000,120,"Acción",false));
        premieres.add(new Movie("12","Princess Peach (?", 5.0f,2000,120,"Acción",false));
        premieres.add(new Movie("12","Fiona", 5.0f,2000,120,"Acción",false));

        mav.addObject("premieres", premieres);

        return mav;
    }

    @RequestMapping("/movie")
    public ModelAndView movie(@RequestParam(value = "movieID", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("movie");
        mav.addObject("movieID", id);
        return mav;
    }
}