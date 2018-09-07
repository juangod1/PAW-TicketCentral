package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Seat;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ScreeningService screeningService;

    @RequestMapping(value = "/json/transaction/getSeatsByScreening/{screeningId}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Seat>> getSeatsByScreening(@PathVariable int screeningId)
    {
        Screening screening = screeningService.getScreeningById(screeningId);
        List<Seat> list = transactionService.getSeatsByScreening(screening);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
