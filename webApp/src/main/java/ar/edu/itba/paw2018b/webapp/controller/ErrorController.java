package ar.edu.itba.paw2018b.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
    @RequestMapping("/403")
    public ModelAndView forbidden() {
        return new ModelAndView("403");
    }
}