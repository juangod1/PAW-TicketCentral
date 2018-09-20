package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.TheatreDao;
import ar.edu.itba.paw2018b.models.Theatre;
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
public class TheatreDaoImpl implements TheatreDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public TheatreDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withSchemaName("public")
                .withTableName("Theatre")
                .usingColumns("TheatreName","Address","City");
    }

    private static final RowMapper<Theatre> ROW_MAPPER =  (rs, i) ->
            new Theatre(rs.getString("TheatreName"),rs.getString("Address"),rs.getString("City") );


    @Override
    public List<Theatre> getAll() {
        List<Theatre> list = jdbcTemplate.query("select * from Theatre", ROW_MAPPER);
        return list;
    }

    @Override
    public Theatre create(String name, String address, String city) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("TheatreName", name);
        entry.put("Address", address);
        entry.put("City", city);

        jdbcInsert.execute(entry);

        return new Theatre(name, address, city);
    }

    @Override
    public int delete(String name) {
        int rows = jdbcTemplate.update("delete from Theatre where TheatreName=?", name);
        return rows;
    }
    @Override
    public Optional<Theatre> getTheatreByName(String name){
        return jdbcTemplate.query("select * from Theatre where TheatreName = ?", ROW_MAPPER,name)
                .stream().findFirst();
    }

}
