package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionsByScreening(int screeningId);
    List<Seat> getSeatsByScreening(int screeningId);
    Integer confirmCheckout(String userDNI, int screeningId, List<String> seatNames, List<String> foodIds);
}
