package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> getTransactionsByScreening(int screeningId);
    List<Transaction> getTransactionsByUserId(int userId);
    Transaction getTransactionById(int id) throws NotFoundException;
    List<Seat> getSeatsByScreening(int screeningId) throws NotFoundException;
    Integer confirmCheckout(int userDNI, int screeningId, List<String> seatNames, List<String> foodIds) throws IllegalArgumentException;
}
