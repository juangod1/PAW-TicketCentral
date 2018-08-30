package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialBlob;


@Controller
public class MovieController {
    @RequestMapping("/movie")
    public ModelAndView movie(@RequestParam(value = "movieID", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("movie");
        try {
            //mav.addObject("chosenMovie", new Movie("12", "Cenicienta", 5.0f, 2000, 1, "Acción", false, new SerialBlob(new byte[]{0})));
        }
        catch(Exception e){};
        return mav;
    }
}
