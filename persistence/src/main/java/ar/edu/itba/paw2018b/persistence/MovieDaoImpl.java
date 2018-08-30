package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.MoviesDao;
import ar.edu.itba.paw2018b.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MoviesDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;


    private static final RowMapper<Movie> ROW_MAPPER =  (rs, i) ->
            new Movie(rs.getString("IMDb"),rs.getString("name"),rs.getFloat("rating"),rs.getInt("year"),rs.getInt("runtime"),rs.getString("genres"), rs.getBoolean("premiere"), rs.getBlob("Image"));

    @Autowired
    public MovieDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withSchemaName("public")
                .withTableName("Movies")
                .usingColumns("IMDb","rating","name","year","runtime","genres","premiere","Image");
    }


    @Override
    public List<Movie> getAll() {
        List<Movie> list = jdbcTemplate.query("select * from Movies", ROW_MAPPER);
        return list;
    }

    @Override
    public List<Movie> getPremieres(){
        List<Movie> list = jdbcTemplate.query("select * from Movies where premiere = true",ROW_MAPPER);
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
    public Movie create(String id, String name, float rating, int year, int runtime, String genres, boolean premiere, Blob img) {
        final Map<String, Object> entry = new HashMap<>();
        entry.put("IMDb", id);
        entry.put("rating", rating);
        entry.put("name", name);
        entry.put("year", year);
        entry.put("runtime", runtime);
        entry.put("genres", genres);
        entry.put("premiere",premiere);
        entry.put("Image",img);
        jdbcInsert.execute(entry);
        return new Movie(id,name,rating,year,runtime,genres,premiere,img);
    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("delete from Movies where IMDb=?", id);

    }
}
