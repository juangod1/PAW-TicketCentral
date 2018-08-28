package ar.edu.itba.paw2018b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("greeting", "PAW");
        LinkedList<String> var = new LinkedList<String>();
        var.add("A");var.add("A");var.add("A");var.add("A");var.add("A");
        mav.addObject("shows", var);
        return mav;
    }

    @RequestMapping("/movie")
    public ModelAndView index(@RequestParam(value = "movieID", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("movie");
        mav.addObject("movieID", id);
        return mav;
    }
}