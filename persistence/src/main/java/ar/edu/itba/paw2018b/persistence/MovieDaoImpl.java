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
import java.io.*;
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
            new Movie(rs.getLong("MovieID"),rs.getString("Title"),rs.getFloat("Rating"),rs.getDate("ReleaseDate"),rs.getInt("Runtime"),rs.getString("Genres"), rs.getBytes("Image"));

    @Autowired
    public MovieDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withSchemaName("public")
                .withTableName("Movies")
                .usingGeneratedKeyColumns("movieid")
                .usingColumns("Rating","Title","ReleaseDate","Runtime","Genres","Image");
    }


    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query("select * from Movies", ROW_MAPPER);
    }

    @Override
    public List<Movie> getPremieres(){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        return jdbcTemplate.query("select * from Movies where ? - ReleaseDate < 7 ",ROW_MAPPER, now);
    }
    @Override
    public List<Movie> getNonPremieres(){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        return jdbcTemplate.query("select * from Movies where ? - ReleaseDate >= 7 ",ROW_MAPPER, now);
    }
    @Override
    public List<Movie> getPremieresByTheatre(String theatre){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        return jdbcTemplate.query("select * from Movies m1 where ? - ReleaseDate < 7 and exists (select * from Screening s1 where m1.movieid= s1.Movie and s1.Theatre = ?) ",ROW_MAPPER,now,theatre);
    }
    @Override
    public List<Movie> getMoviesByTheatre(String theatre){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        return jdbcTemplate.query("select * from Movies m1 where exists (select * from Screening s1 where m1.movieid = s1.Movie and s1.Theatre = ?) ",ROW_MAPPER,theatre);
    }

    @Override
    public Optional<Movie> findMovieByTitle(String name){
        return jdbcTemplate.query("select * from Movies where Title = ?", ROW_MAPPER,name)
                .stream().findFirst();
    }

    @Override
    public Optional<Movie> findMovieById(long id){
        return jdbcTemplate.query("select * from Movies where movieid = ?", ROW_MAPPER,id)
                .stream().findFirst();
    }


    @Override
    public Movie create(String title, float rating, Date releaseDate, int runtime, String genres , byte[] img) {
        final Map<String, Object> entry = new HashMap<>();
        entry.put("Rating", rating);
        entry.put("Title", title);
        entry.put("ReleaseDate",releaseDate);
        entry.put("Runtime", runtime);
        entry.put("Genres", genres);
        entry.put("Image",img);
        Number id = jdbcInsert.executeAndReturnKey(entry);
        return new Movie(id.longValue(),title,rating,releaseDate,runtime,genres,img);
    }


    @Override
    public int delete(long id) {
        return jdbcTemplate.update("delete from Movies where movieid=?", id);

    }

    @Override
    public List<Movie> getRecommendedMoviesForUser(String dni){
        return jdbcTemplate.query("select * from movies where genres in (select m1.genres from screening s1, movies m1 where m1.movieid = s1.movie and s1.screeningid in(select transactions.screeningid from transactions where userdni = ?) group by m1.genres order by count(movie) desc limit 1)", ROW_MAPPER,dni);
    }


}
