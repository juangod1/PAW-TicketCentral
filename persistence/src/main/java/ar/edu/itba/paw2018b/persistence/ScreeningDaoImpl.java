package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.ScreeningDao;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreeningDaoImpl implements ScreeningDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    public ScreeningDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("\"Screening\"")
                .usingGeneratedKeyColumns("ScreeningID")
                .usingColumns("\"Showroom\"","\"Movie\"","\"Time\"","\"Format\"","\"Language\"","\"Theatre\"");
    }
    private static final RowMapper<Screening> ROW_MAPPER = new RowMapper<Screening>() {
        @Override
        public Screening mapRow(ResultSet resultSet, int i) throws SQLException {
            int id =  resultSet.getInt("ScreeningID");
            String showroom = resultSet.getString("Showroom");
            String movie = resultSet.getString("Movie");
            Timestamp time =  resultSet.getTimestamp("Time");
            String format = resultSet.getString("Format");
            String language = resultSet.getString("Language");
            String theatre = resultSet.getString("Theatre");
            return new Screening(id,showroom,movie,time,format,language,theatre);
        }
    };

    @Override
    public List<Screening> getAll() {
        List<Screening> list = jdbcTemplate.query("select * from \"Screening\"", ROW_MAPPER);
        return list;
    }

    @Override
    public List<Screening> getByMovie(Movie m) {
        if(m == null) return null;
        List<Screening> list = jdbcTemplate.query("select * from \"Screening\" where \"Movie\" = ?", ROW_MAPPER,m.getId());
        return list;
    }

    @Override
    public List<Screening> getByTheatre(Theatre t) {
        if(t == null) return null;
        List<Screening> list = jdbcTemplate.query("select * from \"Screening\" where \"Theatre\" = ?", ROW_MAPPER,t.getName());
        return list;
    }

    @Override
    public Screening insert(String showroom, String movie, Timestamp time, String format, String language, String theatre) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"Showroom\"", showroom);
        entry.put("\"Movie\"", movie);
        entry.put("\"Time\"", time);
        entry.put("\"Format\"", format);
        entry.put("\"Language\"", language);
        entry.put("\"Theatre\"", theatre);

        final Number finalid = jdbcInsert.executeAndReturnKey(entry);

        return new Screening((int)finalid,showroom,movie,time,format,language,theatre);
    }

    @Override
    public void delete(String id) {
        if(id == null){
            return;
        }
        jdbcTemplate.update("delete from \"Screening\" where \"ScreeningID\"=?", id);
    }
}
