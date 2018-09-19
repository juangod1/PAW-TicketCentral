package ar.edu.itba.paw2018b.webapp.controller;

import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");


        setUpMovies(mav);
        setUpPremieres(mav);
        setUpFood(mav);

        return mav;
    }

    private void setUpMovies(ModelAndView mav){
        try
        {
            movies = moviesService.getNonPremieres();
        }
        catch(NotFoundException e)
        {
            movies= new ArrayList<>();
        }
        mav.addObject("movies", movies);
    }

    private void setUpPremieres(ModelAndView mav){
        try
        {
            premieres = moviesService.getPremieres();
        }
        catch(NotFoundException e)
        {
            premieres = new ArrayList<>();
        }
        mav.addObject("premieres", premieres);
    }

    private void setUpFood(ModelAndView mav){
        try
        {
            foods = foodService.getFood();
        }
        catch (NotFoundException e)
        {
            foods = new ArrayList<>();
        }
        mav.addObject("foods",foods);
    }
}
