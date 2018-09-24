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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                .usingColumns("Showroom","Movie","ScreeningTime","Format","ScreeningLanguage","Theatre","Price");
    }

    private static final RowMapper<Screening> ROW_MAPPER =  (rs, i) ->
            new Screening(rs.getInt("ScreeningID"),rs.getString("Showroom"),rs.getLong("Movie"),rs.getTimestamp("ScreeningTime"),rs.getString("Format"),rs.getString("ScreeningLanguage"),rs.getString("Theatre"), rs.getInt("Availability"), rs.getInt("Price"));

    @Override
    public List<Screening> getAll() {
        List<Screening> list = jdbcTemplate.query("select * from Screening", ROW_MAPPER);
        return list;
    }

    @Override
    public Optional<Screening> getById(long id) {
        return jdbcTemplate.query("select * from Screening where ScreeningID = ?", ROW_MAPPER, id).stream().findFirst();
    }

    @Override
    public List<Screening> getByMovie(Movie m) {
        if(m == null) return null;
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Screening> list = jdbcTemplate.query("select * from Screening where Movie = ? and screeningtime>?", ROW_MAPPER,m.getId(),now);
        return list;
    }

    @Override
    public List<Screening> getByMovieId(int movieId) {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Screening> list = jdbcTemplate.query("select * from Screening where Movie = ? and screeningtime>? ", ROW_MAPPER,movieId,now);
        return list;
    }


    @Override
    public List<Screening> getByTheatre(Theatre t) {
        if(t == null) return null;
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Screening> list = jdbcTemplate.query("select * from Screening where Theatre = ? and screeningtime > ?", ROW_MAPPER,t.getName(),now);
        return list;
    }

    @Override
    public List<Screening> getByMovieAndTheatre(long movie, String theatre) {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Screening> list = jdbcTemplate.query("select * from Screening where Theatre = ? and Movie = ? and screeningtime > ?", ROW_MAPPER,theatre,movie,now);
        return list;
    }

    @Override
    public Screening create(String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability, int price) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("Showroom", showroom);
        entry.put("Movie", movie);
        entry.put("ScreeningTime", time);
        entry.put("Format", format);
        entry.put("ScreeningLanguage", language);
        entry.put("Theatre", theatre);
        entry.put("Availability", availability);
        entry.put("Price",price);

        final Number finalid = jdbcInsert.executeAndReturnKey(entry);

        return new Screening(finalid.longValue(),showroom,movie,time,format,language,theatre,availability,price);
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update("delete from Screening where ScreeningID=?", id);
    }
}
