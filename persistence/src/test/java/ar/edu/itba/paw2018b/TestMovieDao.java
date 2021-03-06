package ar.edu.itba.paw2018b;


import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.persistence.MovieDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:MovieTestScript.sql")
@Transactional
public class TestMovieDao {


    private static final float RATING = (float)8.5;
    private static final String NAME = "FARGO";
    private static final Date RELEASEDATE = Date.valueOf("2018-9-13");
    private static final int RUNTIME = 120;
    private static final String GENRES = "Drama";
    private static final File IMAGE = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\test\\resources\\fargo.jpg");
    private static final long MOVIE_ID = 2;
    private static final long NONPREMIEREMOVIE_ID = 3;
    private static final long NONEXISTENTEMOVIE_ID = 4;
    private byte[] BYTES;

    @Autowired
    private MovieDaoImpl movieDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Test
    public void testCreateMovie(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Movies");
        try {
            FileInputStream fis = new FileInputStream(IMAGE);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) {
            }
            BYTES = bos.toByteArray();

        } catch (FileNotFoundException f)
        {
            System.out.println("File not found");
        }
        final Movie movie = movieDao.create(NAME,RATING,RELEASEDATE,RUNTIME,GENRES,BYTES);
        Assert.assertNotNull(movie);
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies"));
    }

    @Test
    public void testFindById(){
        final Optional<Movie> movie = movieDao.findMovieById(MOVIE_ID);

        Assert.assertTrue(movie.isPresent());
        Assert.assertEquals(MOVIE_ID,movie.get().getId());
        Assert.assertEquals(NAME,movie.get().getName());
    }

//    @Test
//    public void testGetPremieres(){
//        List<Movie> movieList = movieDao.getPremieres();
//        Assert.assertEquals(1,movieList.size());
//        Assert.assertEquals(MOVIE_ID,movieList.get(0).getId());
//    }
//
//    @Test
//    public void testGetNonPremieres(){
//        List<Movie> movieList = movieDao.getNonPremieres();
//
//        Assert.assertEquals(1,movieList.size());
//        Assert.assertEquals(NONPREMIEREMOVIE_ID,movieList.get(0).getId());
//    }

//    @Test
//    public void testDeleteExistingMovie() {
//        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies");
//        int rowsAffected = movieDao.delete(MOVIE_ID);
//        Assert.assertEquals(1,rowsAffected);
//        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies");
//        Assert.assertEquals(before,after+1);
//    }
//
//    @Test
//    public void testDeleteNonExistingMovie() {
//        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies");
//        int rowsAffected = movieDao.delete(NONEXISTENTEMOVIE_ID);
//        Assert.assertEquals(0,rowsAffected);
//        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies");
//        Assert.assertEquals(before,after);
//    }
}
