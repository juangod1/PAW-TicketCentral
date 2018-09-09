package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.Transaction;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionDao {

    List<Transaction> getAll();

    Transaction create(String user, int ScreeningId, String seat, double price, boolean paid, Timestamp date );

    List<Transaction> getOcuppiedSeatsByScreening(int screeningId);

    void delete(String id);

    void transformIntoBuy(String id);
}
