package ar.edu.itba.paw2018b.webapp.controller;

import ar.edu.itba.paw2018b.interfaces.service.FoodService;
import ar.edu.itba.paw2018b.interfaces.service.MoviesService;
import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.EmailExistsException;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import ar.edu.itba.paw2018b.webapp.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");

        setUpMovies(mav);
        setUpPremieres(mav);
        setUpFood(mav);

        return mav;
    }
    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("registerForm") final UserForm form) {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, final BindingResult errors){
        if(errors.hasErrors()){
            return register(form);
        }
        try {
            final User u = userService.create(form.getDni(), form.getName(), form.getSurname(), form.getUsername(),passwordEncoder.encode(form.getPassword()), form.getPhone(), form.getEmail());
        }
        catch (EmailExistsException emailExists){
            errors.rejectValue("email","message.regError");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }
    

    private void setUpMovies(ModelAndView mav){
        List<Movie> movies;
        try
        {
            movies = moviesService.getNonPremieres();
        }
        catch(NotFoundException e)
        {
            movies = new ArrayList<>();
        }
        mav.addObject("movies", movies);
    }

    private void setUpPremieres(ModelAndView mav){
        List<Movie> premieres;
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
        List<Food> foods;
        try
        {
            foods = foodService.getFood();
        }
        catch (NotFoundException e)
        {
            foods = new ArrayList<>();
        }
        mav.addObject("foods", foods);
    }
}
