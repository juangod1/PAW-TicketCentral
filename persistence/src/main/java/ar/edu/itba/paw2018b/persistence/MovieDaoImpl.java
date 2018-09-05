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
            new Movie(rs.getLong("IMDb"),rs.getString("Title"),rs.getFloat("Rating"),rs.getDate("ReleaseDate"),rs.getInt("Runtime"),rs.getString("Genres"), rs.getBytes("Image"));

    @Autowired
    public MovieDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withSchemaName("public")
                .withTableName("Movies")
                .usingGeneratedKeyColumns("imdb")
                .usingColumns("Rating","Title","ReleaseDate","Runtime","Genres","Image");
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
        List<Movie> list = jdbcTemplate.query("select * from Movies where ? - ReleaseDate < 7 ",ROW_MAPPER, now);
        return list;
    }
    @Override
    public List<Movie> getPremieresByTheatre(String theatre){
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        List<Movie> list = jdbcTemplate.query("select * from Movies m1 where ? - ReleaseDate < 7 and exists (select * from Screening s1 where m1.IMDb = s1.Movie and s1.Theatre = ?) ",ROW_MAPPER,now,theatre);
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
        return jdbcTemplate.query("select * from Movies where Title = ?", ROW_MAPPER,name)
                .stream().findFirst();
    }

    @Override
    public Optional<Movie> findMovieById(long id){
        return jdbcTemplate.query("select * from Movies where IMDb = ?", ROW_MAPPER,id)
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
    public void setUpMovies(){
        jdbcTemplate.update("delete from Movies");
        final Map<String, Object> entry = new HashMap<>();
        entry.put("Rating", 6.6);
        entry.put("Title", "Justice League");
        entry.put("ReleaseDate",new Date(System.currentTimeMillis()));
        entry.put("Runtime", 120);
        entry.put("Genres", "Action,Adventure,Fantasy");
        File IMAGE = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\main\\resources\\justiceleague.jpg");
        byte[] img = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found"); }
        entry.put("Image",img);
        jdbcInsert.executeAndReturnKey(entry);

        final Map<String, Object> entry1 = new HashMap<>();
        entry1.put("Rating", 7.5);
        entry1.put("Title", "Wonder Woman");
        entry1.put("ReleaseDate",new Date(System.currentTimeMillis()));
        entry1.put("Runtime", 141);
        entry1.put("Genres", "Action,Adventure,Fantasy");
        File IMAGE1 = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\main\\resources\\wonderwoman.jpg");
        byte[] img1 = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE1);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img1 = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found"); }
        entry1.put("Image",img1);
        jdbcInsert.executeAndReturnKey(entry1);
    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("delete from Movies where IMDb=?", id);

    }
}
