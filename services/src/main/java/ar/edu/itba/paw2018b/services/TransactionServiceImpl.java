package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.interfaces.service.TransactionService;
import ar.edu.itba.paw2018b.models.Seat;
import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Override
    public Boolean confirmCheckout(User user, Screening screening, List<Seat> seats, List<Food> foods) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        //transactionDao.create()
        return false;
    }
}
