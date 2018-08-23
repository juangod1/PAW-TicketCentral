package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.UserDao;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    public UserDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("\"User\"")
                .usingColumns("\"DNI\"","\"Name\"","\"Surname\"","\"MobilePhone\"","\"Email\"");
    }

    private static final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            String dni = resultSet.getString("DNI");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");
            String phone = resultSet.getString("MobilePhone");
            String email = resultSet.getString("Email");
            return new User(dni,name,surname,phone,email);
        }
    };

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("select * from \"User\"", ROW_MAPPER);
        return list;
    }

    @Override
    public void insert(String dni, String name, String surname, String phone, String email) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"DNI\"", dni);
        entry.put("\"Name\"", name);
        entry.put("\"Surname\"", surname);
        entry.put("\"MobilePhone\"", phone);
        entry.put("\"Email\"", email);

        jdbcInsert.execute(entry);
    }

    @Override
    public void delete(String dni) {
        if(dni == null)
            return;
        jdbcTemplate.update("delete from \"User\" where \"DNI\"=?", dni);
    }
}
