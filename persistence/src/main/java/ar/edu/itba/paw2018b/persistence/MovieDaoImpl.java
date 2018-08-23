package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.MoviesDao;
import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieDaoImpl implements MoviesDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<Movie> ROW_MAPPER = new RowMapper<Movie>() {


        @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            String id =  resultSet.getString("IMDb");
            String name = resultSet.getString("name");
            float rating = resultSet.getFloat("rating");
            int year = resultSet.getInt("year");
            int runtime = resultSet.getInt("runtime");
            String genres = resultSet.getString("genres");
            boolean premiere = resultSet.getBoolean("premiere");
            return new Movie(id,name,rating,year,runtime,genres,premiere);
        }
    };
    @Autowired
    public MovieDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withSchemaName("public")
                .withTableName("\"Movies\"")
                .usingColumns("\"IMDb\"","\"rating\"","\"name\"","\"year\"","\"runtime\"","\"genres\"","\"premiere\"");
    }


    @Override
    public List<Movie> getAll() {
        List<Movie> list = jdbcTemplate.query("select * from \"Movies\"", ROW_MAPPER);
        return list;
    }

    @Override
    public List<Movie> getPremieres(){
        List<Movie> list = jdbcTemplate.query("select * from \"Movies\" where premiere = true",ROW_MAPPER);
        return list;
    }

    @Override
    public Movie findMovieByTitle(String name){
        List<Movie> m = jdbcTemplate.query("select * from \"Movies\" where name = ?", ROW_MAPPER,name);
        if(m.size() != 1)
            return null;
        return m.get(0);
    }
    @Override
    public void insert(String id, String name, float rating, int year, int runtime, String genres, boolean premiere) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"IMDb\"", id);
        entry.put("\"rating\"", rating);
        entry.put("\"name\"", name);
        entry.put("\"year\"", year);
        entry.put("\"runtime\"", runtime);
        entry.put("\"genres\"", genres);
        entry.put("\"premiere\"",premiere);
        jdbcInsert.execute(entry);

    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("delete from \"Movies\" where \"IMDb\"=?", id);

    }
}
