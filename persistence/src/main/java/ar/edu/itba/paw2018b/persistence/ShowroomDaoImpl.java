package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.ShowroomsDao;
import ar.edu.itba.paw2018b.models.Showroom;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowroomDaoImpl implements ShowroomsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    public ShowroomDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("\"Showrooms\"")
                .usingColumns("\"Theatre\"","\"ShowroomName\"","\"Capacity\"","\"Layout\"");
    }

    private static final RowMapper<Showroom> ROW_MAPPER = new RowMapper<Showroom>() {
        @Override
        public Showroom mapRow(ResultSet resultSet, int i) throws SQLException {
            int capacity =  resultSet.getInt("Capacity");
            String showroom = resultSet.getString("Showroom");
            String layout = resultSet.getString("Layout");
            String theatre = resultSet.getString("Theatre");
            return new Showroom(theatre,showroom,capacity,layout);
        }
    };

    @Override
    public List<Showroom> getByTheatre(String theatreName) {
        List<Showroom> list = jdbcTemplate.query("select * from \"Showrooms\" where \"Theatre\" = ?", ROW_MAPPER, theatreName);
        return list;
    }

    @Override
    public int getCapacity(String theatreName, String showroomName) {
        List<Showroom> list = jdbcTemplate.query("select * from \"Showrooms\" where \"Theatre\" = ? and \"ShowroomName\" = ?", ROW_MAPPER, theatreName, showroomName);
        if(list.size()!=1)
            return -1;
        return list.get(0).getCapacity();
    }

    @Override
    public Showroom getShowroom(String theatreName, String showroomName) {
        List<Showroom> list = jdbcTemplate.query("select * from \"Showrooms\" where \"Theatre\" = ? and \"ShowroomName\" = ?", ROW_MAPPER, theatreName, showroomName);
        if(list.size()!=1)
            return null;
        return list.get(0);
    }

    @Override
    public Showroom insert(String theatre, String showroom, int capacity, String layout) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"Theatre\"", theatre);
        entry.put("\"ShowroomName\"", showroom);
        entry.put("\"Capacity\"", capacity);
        entry.put("\"Layout\"", layout);

        return new Showroom(theatre,showroom,capacity,layout);
    }

    @Override
    public void delete(String theatre, String showroomName) {
        jdbcTemplate.update("delete from \"Showroom\" where \"Theatre\"=? and \"ShowroomName\" = ?", theatre,showroomName);
    }
}
