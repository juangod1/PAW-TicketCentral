package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class SeatPickerController {
    @RequestMapping("/seatPicker")
    public ModelAndView seatPicker(@RequestParam(value = "movieID", required = true) final int id,
                                   @RequestParam(value = "time", required = true) final int time,
                                   @RequestParam(value = "amount", required = true) final int amount) {
        final ModelAndView mav = new ModelAndView("seatPicker");

        return mav;
    }
}
