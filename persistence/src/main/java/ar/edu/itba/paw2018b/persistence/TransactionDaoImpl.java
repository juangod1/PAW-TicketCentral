package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
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

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public TransactionDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withSchemaName("public")
                .withTableName("\"Transaction\"")
                .usingColumns("\"TransID\"","\"User\"","\"Screening ID\"","\"Seat\"","\"Price\"","\"Paid\"", "\"Date\"");
    }

    private static final RowMapper<Transaction> ROW_MAPPER =  (rs, i) ->
            new Transaction(rs.getInt("TransID"),rs.getString("User"), rs.getInt("Screening ID"), rs.getString("Seat"),rs.getDouble("Price"), rs.getTimestamp("Date"), rs.getBoolean("Paid"));

    @Override
    public List<Transaction> getAll() {
        List<Transaction> list = jdbcTemplate.query("select * from \"Transaction\"", ROW_MAPPER);
        return list;
    }

    @Override
    public Transaction create(int id, String user, int ScreeningId, String seat, double price, boolean paid, Timestamp date) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"TransID\"", id);
        entry.put("\"User\"", user);
        entry.put("\"Screening ID\"", ScreeningId);
        entry.put("\"Seat\"", seat);
        entry.put("\"Price\"", price);
        entry.put("\"Date\"", date);
        entry.put("\"Paid\"", paid);

        jdbcInsert.execute(entry);
        return new Transaction(id,user,ScreeningId,seat,price,date,paid);
    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("DELETE FROM \"Transaction\" WHERE \"TransID\" = ?", id);
    }

    public void transformIntoBuy(String id) {
        jdbcTemplate.update("UPDATE \"Transaction\" SET \"paid\" = TRUE WHERE \"TransID\" = ?", id);
    }
}

