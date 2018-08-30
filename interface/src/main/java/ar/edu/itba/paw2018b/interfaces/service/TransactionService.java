package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.*;

import java.util.List;

public interface TransactionService {
    void confirmCheckout(User user, Screening screening,List<Booth> booths,List<Food> foods);
}
