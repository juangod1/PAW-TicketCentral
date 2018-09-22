package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.models.Transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TransactionDao {

    List<Transaction> getAll();

    Transaction create(int userId, int ScreeningId, String seat, String food, double price, boolean paid, Timestamp date );

    List<Transaction> getTransactionsByScreening(int screeningId);

    int delete(int id);

    int transformIntoBuy(int id);

    List<Transaction> getTransactionsByUserId(int userId);

    Optional<Transaction> getTransactionById(int id);
}
