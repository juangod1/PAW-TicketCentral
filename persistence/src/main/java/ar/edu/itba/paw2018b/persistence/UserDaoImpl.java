package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.UserDao;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
                .usingGeneratedKeyColumns("userid")
                .usingColumns("DNI","FirstName","Surname","MobilePhone","Email","Username","Password","isAdmin");
    }

    private static final RowMapper<User> ROW_MAPPER =  (rs, i) ->
            new User(rs.getLong("UserID"),rs.getString("DNI"), rs.getString("FirstName"), rs.getString("Surname"), rs.getString("Username"), rs.getString("Password"), rs.getString("MobilePhone"), rs.getString("Email"), rs.getBoolean("isAdmin"));

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("select * from Users", ROW_MAPPER);
        return list;
    }

    @Override
    public Optional<User> findByDni(String dni) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE dni = ?", ROW_MAPPER, dni)
            .stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE email = ?", ROW_MAPPER, email)
                .stream().findFirst();
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE userid = ?", ROW_MAPPER, id)
                .stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE username = ?", ROW_MAPPER, username)
                .stream().findFirst();
    }

    @Override
    public User create(String dni, String name, String surname, String username, String password, String phone, String email, boolean isAdmin) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("DNI", dni);
        entry.put("FirstName", name);
        entry.put("Surname", surname);
        entry.put("MobilePhone", phone);
        entry.put("Email", email);
        entry.put("Username", username);
        entry.put("Password", password);
        entry.put("isAdmin",isAdmin);

        Number id = jdbcInsert.executeAndReturnKey(entry);
        return new User(id.longValue(), dni, name, surname, username, password, phone, email,isAdmin);
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update("delete from Users where userid=?", id);
    }
}
