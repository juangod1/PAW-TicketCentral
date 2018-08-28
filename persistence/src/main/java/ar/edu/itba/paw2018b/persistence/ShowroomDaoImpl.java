package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.ShowroomsDao;
import ar.edu.itba.paw2018b.models.Showroom;
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
public class ShowroomDaoImpl implements ShowroomsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ShowroomDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("\"Showrooms\"")
                .usingColumns("\"Theatre\"","\"ShowroomName\"","\"Capacity\"","\"Layout\"");
    }

    private static final RowMapper<Showroom> ROW_MAPPER =  (rs, i) ->
            new Showroom(rs.getString("Theatre"),rs.getString("Showroom"),rs.getInt("Capacity"),rs.getString("Layout"));


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

//    @Override
//    public Optional<Movie> findMovieByTitle(String name){
//        return jdbcTemplate.query("select * from \"Movies\" where name = ?", ROW_MAPPER,name)
//                .stream().findFirst();
//    }
    @Override
    public Optional<Showroom> getShowroom(String theatreName, String showroomName) {
        return jdbcTemplate.query("select * from \"Showrooms\" where \"Theatre\" = ? and \"ShowroomName\" = ?", ROW_MAPPER, theatreName, showroomName)
                .stream().findFirst();
    }

    @Override
    public Showroom create(String theatre, String showroom, int capacity, String layout) {
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
