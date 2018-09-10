package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    private List<Movie> movies;
    private List<Movie> premieres;
    private List<Food> foods;

    @Autowired
    private MoviesService moviesService;
    @Autowired
    private FoodService foodService;

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");


        setUpMovies(mav);
        setUpPremieres(mav);
        setUpFood(mav);

        return mav;
    }

    private void setUpMovies(ModelAndView mav){
        movies = moviesService.getNonPremieres();
        mav.addObject("movies", movies);
    }

    private void setUpPremieres(ModelAndView mav){
        premieres = moviesService.getPremieres();
        mav.addObject("premieres", premieres);
    }

    private void setUpFood(ModelAndView mav){
        foods = foodService.getFood();
        mav.addObject("foods",foods);
    }
}
