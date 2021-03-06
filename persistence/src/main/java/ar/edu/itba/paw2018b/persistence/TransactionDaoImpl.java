package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public TransactionDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("Transactions")
                .usingGeneratedKeyColumns("transid")
                .usingColumns("UserId","ScreeningID","Seat","FoodDetails", "Price","Paid", "TransactionDate");
    }

    private static final RowMapper<Transaction> ROW_MAPPER =  (rs, i) ->
            new Transaction(rs.getInt("TransID"),rs.getInt("UserId"), rs.getInt("ScreeningID"), rs.getString("Seat"),rs.getString("FoodDetails"),rs.getDouble("Price"), rs.getTimestamp("TransactionDate"), rs.getBoolean("Paid"));

    @Override
    public List<Transaction> getAll() {
        List<Transaction> list = jdbcTemplate.query("select * from Transactions", ROW_MAPPER);
        return list;
    }

    @Override
    public Transaction create(int userId, int ScreeningId, String seat, String food, double price, boolean paid, Timestamp date) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("UserId", userId);
        entry.put("ScreeningID", ScreeningId);
        entry.put("Seat", seat);
        entry.put("FoodDetails", food);
        entry.put("Price", price);
        entry.put("TransactionDate", date);
        entry.put("Paid", paid);

        Number id = jdbcInsert.executeAndReturnKey(entry);
        return new Transaction(id.intValue(),userId,ScreeningId,seat,food,price,date,paid);
    }

    @Override
    public List<Transaction> getTransactionsByScreening(int screeningId) {
        List<Transaction> list = jdbcTemplate.query("select * from Transactions where screeningid = ?", ROW_MAPPER, screeningId);
        return list;
    }


    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Transactions WHERE TransID = ?", id);
    }
    @Override
    public int transformIntoBuy(int id) {
       return jdbcTemplate.update("UPDATE Transactions SET paid = TRUE WHERE TransID = ?", id);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> list = jdbcTemplate.query("select * from Transactions where UserId = ?", ROW_MAPPER, userId);
        return list;
    }

    @Override
    public Optional<Transaction> getTransactionById(int id) {
        return jdbcTemplate.query("SELECT * FROM Transactions WHERE transid = ?", ROW_MAPPER, id)
                .stream().findFirst();
    }

}

