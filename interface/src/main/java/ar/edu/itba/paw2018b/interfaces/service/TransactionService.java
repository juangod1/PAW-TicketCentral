package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;

public interface TransactionService {
    Boolean confirmCheckout(User user, Screening screening, List<Seat> seats, List<Food> foods);
}
