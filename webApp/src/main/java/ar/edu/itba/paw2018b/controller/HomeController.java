package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

@Controller
public class HomeController {
    private LinkedList<Movie> movies;
    private LinkedList<Movie> premieres;

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");

        setUpMovies(mav);
        setUpPremieres(mav);

        return mav;
    }

    private void setUpMovies(ModelAndView mav){
         movies = new LinkedList<>(); // getMovies service

        movies.add(new Movie("12","Sharknado", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("13132","Sharky Sharky", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("142","Sharknado IV", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("112","69 sharknados", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("142","Shark Dog Thing", 5.0f,2000,120,"Acción",false));
        movies.add(new Movie("152","Attack Of The Sharks", 5.0f,2000,120,"Acción",false));

        mav.addObject("movies", movies);
    }

    private void setUpPremieres(ModelAndView mav){
        premieres = new LinkedList<>(); // getPremieres service

        premieres.add(new Movie("182","Cenicienta", 5.0f,2000,120,"Acción",false));
        premieres.add(new Movie("712","Princess Peach (?", 5.0f,2000,120,"Acción",false));
        premieres.add(new Movie("912","Fiona", 5.0f,2000,120,"Acción",false));

        mav.addObject("premieres", premieres);
    }
}
