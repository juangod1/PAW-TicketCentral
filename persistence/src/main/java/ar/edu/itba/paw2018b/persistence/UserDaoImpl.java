package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.UserDao;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public UserDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("Users")
                .usingColumns("DNI","FirstName","Surname","MobilePhone","Email");
    }

    private static final RowMapper<User> ROW_MAPPER =  (rs, i) ->
            new User(rs.getString("DNI"), rs.getString("FirstName"), rs.getString("Surname"), rs.getString("MobilePhone"), rs.getString("Email"));

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("select * from Users", ROW_MAPPER);
        return list;
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE userid = ?", ROW_MAPPER, id)
            .stream().findFirst();
    }

    @Override
    public User create(String dni, String name, String surname, String phone, String email) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("DNI", dni);
        entry.put("FirstName", name);
        entry.put("Surname", surname);
        entry.put("MobilePhone", phone);
        entry.put("Email", email);

        jdbcInsert.execute(entry);
        return new User(dni, name, surname, phone, email);
    }

    @Override
    public void delete(String dni) {
        if(dni == null)
            return;
        jdbcTemplate.update("delete from Users where DNI=?", dni);
    }
}
