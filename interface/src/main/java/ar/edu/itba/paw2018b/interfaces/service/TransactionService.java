package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> getTransactionsByScreening(int screeningId);
    List<Transaction> getTransactionsByUserId(int userId);
    Transaction getTransactionById(int id);
    List<Seat> getSeatsByScreening(int screeningId);
    Integer confirmCheckout(int userDNI, int screeningId, List<String> seatNames, List<String> foodIds);
}
