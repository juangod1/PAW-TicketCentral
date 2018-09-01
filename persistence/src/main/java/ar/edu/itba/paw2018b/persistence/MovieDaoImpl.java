package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.MoviesDao;
import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MoviesDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;


    private static final RowMapper<Movie> ROW_MAPPER =  (rs, i) ->
            new Movie(rs.getString("IMDb"),rs.getString("name"),rs.getFloat("rating"),rs.getDate("ReleaseDate"),rs.getInt("runtime"),rs.getString("genres"), rs.getBytes("Image"));

    @Autowired
    public MovieDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withSchemaName("public")
                .withTableName("Movies")
                .usingColumns("IMDb","Rating","Name","ReleaseDate","Runtime","Genres","Image");
    }


    @Override
    public List<Movie> getAll() {
        List<Movie> list = jdbcTemplate.query("select * from Movies", ROW_MAPPER);
        return list;
    }

    @Override
    public List<Movie> getPremieres(){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Movie> list = jdbcTemplate.query("select * from Movies where now - ReleaseDate < 7 ",ROW_MAPPER);
        return list;
    }
    @Override
    public List<Movie> getPremieresByTheatre(String theatre){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Movie> list = jdbcTemplate.query("select * from Movies m1 where now - ReleaseDate < 7 and exists (select * from Screening s1 where m1.IMDb = s1.Movie and s1.Theatre = ?) ",ROW_MAPPER,theatre);
        return list;
    }
    @Override
    public List<Movie> getMoviesByTheatre(String theatre){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Movie> list = jdbcTemplate.query("select * from Movies m1 where exists (select * from Screening s1 where m1.IMDb = s1.Movie and s1.Theatre = ?) ",ROW_MAPPER,theatre);
        return list;
    }

    @Override
    public Optional<Movie> findMovieByTitle(String name){
        return jdbcTemplate.query("select * from Movies where name = ?", ROW_MAPPER,name)
                .stream().findFirst();
    }

    @Override
    public Optional<Movie> findMovieById(String id){
        return jdbcTemplate.query("select * from Movies where IMDb = ?", ROW_MAPPER,id)
                .stream().findFirst();
    }


    @Override
    public Movie create(String id, String name, float rating, Date releaseDate, int runtime, String genres , byte[] img) {
        final Map<String, Object> entry = new HashMap<>();
        entry.put("IMDb", id);
        entry.put("Rating", rating);
        entry.put("Name", name);
        entry.put("ReleaseDate",releaseDate);
        entry.put("Runtime", runtime);
        entry.put("Genres", genres);
        entry.put("Image",img);
        jdbcInsert.execute(entry);
        return new Movie(id,name,rating,releaseDate,runtime,genres,img);
    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("delete from Movies where IMDb=?", id);

    }
}
