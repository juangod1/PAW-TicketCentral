package ar.edu.itba.paw2018b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
    @RequestMapping("/movie")
    public ModelAndView movie(@RequestParam(value = "movieID", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("movie");
        mav.addObject("movieID", id);
        return mav;
    }
}
