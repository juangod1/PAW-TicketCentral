package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.TheatreDao;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreDaoImpl implements TheatreDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    public TheatreDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withSchemaName("public")
                .withTableName("\"Theatre\"")
                .usingColumns("\"Name\"","\"Address\"","\"City\"");
    }

    private static final RowMapper<Theatre> ROW_MAPPER = new RowMapper<Theatre>() {
        @Override
        public Theatre mapRow(ResultSet resultSet, int i) throws SQLException {
            String name = resultSet.getString("Name");
            String address= resultSet.getString("Address");
            String city = resultSet.getString("City");
            return new Theatre(name,address,city);
        }
    };

    @Override
    public List<Theatre> getAll() {
        List<Theatre> list = jdbcTemplate.query("select * from \"Theatre\"", ROW_MAPPER);
        return list;
    }

    @Override
    public void insert(String name, String address, String city) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"Name\"", name);
        entry.put("\"Address\"", address);
        entry.put("\"City\"", city);

        jdbcInsert.execute(entry);
    }

    @Override
    public void delete(String name) {
        if(name == null)
            return;
        jdbcTemplate.update("delete from \"Theatre\" where \"Name\"=?", name);
    }
    @Override
    public Theatre getTheatreByName(String name){
        List<Theatre> t = jdbcTemplate.query("select * from \"Theatre\" where \"Name\" = ?", ROW_MAPPER,name);
        if(t.size() != 1)
            return null;
        return t.get(0);
    }
}
