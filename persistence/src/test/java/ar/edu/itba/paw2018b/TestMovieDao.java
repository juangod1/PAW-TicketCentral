package ar.edu.itba.paw2018b;


import ar.edu.itba.paw2018b.models.Movie;
import ar.edu.itba.paw2018b.persistence.MovieDaoImpl;
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

import javax.sql.DataSource;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:MovieTestScript.sql")
public class TestMovieDao {

    private long IMDB = 1;
    private float RATING = (float)8.5;
    private String NAME = "FARGO";
    private Date RELEASEDATE = new Date(System.currentTimeMillis());
    private int RUNTIME = 120;
    private String GENRES = "Drama";
    private File IMAGE = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\test\\resources\\fargo.jpg");
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
        Assert.assertEquals(IMDB,movie.getId());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Movies"));
    }


}
