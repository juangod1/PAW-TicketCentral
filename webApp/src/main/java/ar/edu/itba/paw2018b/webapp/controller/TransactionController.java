package ar.edu.itba.paw2018b.webapp.controller;

import ar.edu.itba.paw2018b.interfaces.service.*;
import ar.edu.itba.paw2018b.models.Seat;
import ar.edu.itba.paw2018b.models.Transaction;
import ar.edu.itba.paw2018b.models.TransactionRequest;
import ar.edu.itba.paw2018b.models.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@Controller
public class TransactionController {

    @Autowired
    MoviesService moviesService;

    @Autowired
    EmailService emailService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    UserService userService;


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

    @RequestMapping(value = "/json/transaction/getTransactionsByUser/{userId}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Transaction>> getTransactionsByUser(@PathVariable int userId)
    {
        List<Transaction> list = transactionService.getTransactionsByUserId(userId);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/transaction/getTransactionById/{id}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<Transaction> getTransactionsById(@PathVariable int id)
    {
        Transaction transaction = transactionService.getTransactionById(id);
        if(transaction==null)
        {
            return new ResponseEntity<>(transaction, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/transaction/getTransactionsByScreening/{screeningId}", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Transaction>> getTransactionsByScreening(@PathVariable int screeningId)
    {
        List<Transaction> list = transactionService.getTransactionsByScreening(screeningId);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/transaction/confirmCheckout", method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<Integer> confirmCheckout(@RequestBody String transactionJson) throws JsonParseException, IOException
    {
        TransactionRequest transactionRequest = new ObjectMapper().readValue(transactionJson, new TypeReference<TransactionRequest>() { });
        User user = userService.findById(transactionRequest.getUserId());

        Integer id = transactionService.confirmCheckout(transactionRequest.getUserId(),transactionRequest.getScreeningID(),transactionRequest.getSeatNames(),transactionRequest.getFoodDetails());
        if(id==null)
        {
            return new ResponseEntity<>(id, HttpStatus.NOT_MODIFIED);
        }

        if(transactionRequest.getSendMail()){
            emailService.sendReservationEmail(transactionService.getTransactionById(id), user,transactionRequest);
        }

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @RequestMapping(value = "/json/transaction/getMyTransactions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Transaction>> currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        List<Transaction> transactions = transactionService.getTransactionsByUserId((int)userService.findByUsername(principal.getName()).getId());

        if(principal == null){
            return new ResponseEntity<>(transactions,HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

}
