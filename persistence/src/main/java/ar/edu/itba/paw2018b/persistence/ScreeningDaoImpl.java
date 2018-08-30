package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.ScreeningDao;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Theatre;
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
public class ScreeningDaoImpl implements ScreeningDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ScreeningDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("Screening")
                .usingGeneratedKeyColumns("ScreeningID")
                .usingColumns("Showroom","Movie","Time","Format","Language","Theatre");
    }

    private static final RowMapper<Screening> ROW_MAPPER =  (rs, i) ->
            new Screening(rs.getInt("ScreeningID"),rs.getString("Showroom"),rs.getString("Movie"),rs.getTimestamp("Time"),rs.getString("Format"),rs.getString("Language"),rs.getString("Theatre"));

    @Override
    public List<Screening> getAll() {
        List<Screening> list = jdbcTemplate.query("select * from Screening", ROW_MAPPER);
        return list;
    }

    @Override
    public List<Screening> getById(int id) {
        List<Screening> list = jdbcTemplate.query("select * from Screening where ScreeningID = ?", ROW_MAPPER, id);
        return list;
    }

    @Override
    public List<Screening> getByMovie(Movie m) {
        if(m == null) return null;
        List<Screening> list = jdbcTemplate.query("select * from Screening where Movie = ?", ROW_MAPPER,m.getId());
        return list;
    }

    @Override
    public List<Screening> getByTheatre(Theatre t) {
        if(t == null) return null;
        List<Screening> list = jdbcTemplate.query("select * from Screening where Theatre = ?", ROW_MAPPER,t.getName());
        return list;
    }

    @Override
    public List<Screening> getByMovieAndTheatre(String movie, String theatre) {
        List<Screening> list = jdbcTemplate.query("select * from Screening where Theatre = ? and Movie = ?", ROW_MAPPER,theatre,movie);
        return list;
    }

    @Override
    public Screening create(String showroom, String movie, Timestamp time, String format, String language, String theatre) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("Showroom", showroom);
        entry.put("Movie", movie);
        entry.put("Time", time);
        entry.put("Format", format);
        entry.put("Language", language);
        entry.put("Theatre", theatre);

        final Number finalid = jdbcInsert.executeAndReturnKey(entry);

        return new Screening(finalid.intValue(),showroom,movie,time,format,language,theatre);
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("delete from Screening where ScreeningID=?", id);
    }
}
