package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class SeatPickerController {

    public int hashSeatNumber(int row, int column){
        // Función de paridad de Cantor
        // f: N**2 --> N, f es biyectiva
        // https://en.wikipedia.org/wiki/Cantor_pairing_function
        return (row+column+1)*(row+column)/2 + column;
    }

    private int seatRowsMAX = 12;
    private int seatColumnsMAX = 16;

    @RequestMapping("/seatPicker")
    public ModelAndView seatPicker(@RequestParam(value = "movieID", required = true) final int id,
                                   @RequestParam(value = "time", required = true) final int time,
                                   @RequestParam(value = "amount", required = true) final int amount) {
        final ModelAndView mav = new ModelAndView("seatPicker");

        int i = 7;
        int j = 7;
        HashMap<Integer,Seat> seats = new HashMap<>();

        while(i-->0){
            while(j-->0){
                seats.put(hashSeatNumber(i,j),new Seat(i,j,true,""+i+"-"+j));
            }
            j=7;
        }
        
        mav.addObject("seats",seats);
        mav.addObject("seatRowsMax",seatRowsMAX);
        mav.addObject("seatColumnsMax",seatColumnsMAX);

        return mav;
    }
}
