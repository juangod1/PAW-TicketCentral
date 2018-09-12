package ar.edu.itba.paw2018b.controller;

import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Seat;
import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.TransactionRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        List<Seat> list = transactionService.getSeatsByScreening(screeningId);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/transaction/confirmCheckout", method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<Integer> getHoursByScreenings(@RequestBody String screeningJson) throws JsonParseException, IOException
    {
        TransactionRequest transactionRequest = new ObjectMapper().readValue(screeningJson, new TypeReference<TransactionRequest>() { });

        Integer id = transactionService.confirmCheckout(transactionRequest.getUserDNI(),transactionRequest.getScreeningID(),transactionRequest.getSeatNames(),transactionRequest.getFoodDetails());
        if(id==null)
        {
            return new ResponseEntity<>(id, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
