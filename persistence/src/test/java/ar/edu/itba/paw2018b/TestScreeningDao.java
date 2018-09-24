package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.persistence.ScreeningDaoImpl;
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

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:ScreeningTestScript.sql")
public class TestScreeningDao {
    private static final String SHOWROOM = "ATLAS 1";
    private static final long MOVIE = 1;
    private static final Timestamp TIME = Timestamp.valueOf("2100-9-20 20:00:00");
    private static final String FORMAT = "2D";
    private static final String LANGUAGE = "SUBTITULADO";
    private static final String THEATRE = "ATLAS NORTE";
    private static final int PRICE = 120;
    private static final int AVAILABILITY = 100;
    private static final long SCREENING_ID = 5;
    private static final long NONEXISTENTSCREENING_ID = 7;

    @Autowired
    private ScreeningDaoImpl screeningDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Screening");
    }

    @Test
    public void testCreateScreening(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Screening");
        final Screening screening = screeningDao.create(SHOWROOM,MOVIE,TIME,FORMAT,LANGUAGE,THEATRE,AVAILABILITY,PRICE);
        Assert.assertNotNull(screening);
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening"));
    }

    @Test
    public void testFindById(){
        final Optional<Screening> screening = screeningDao.getById(SCREENING_ID);
        Assert.assertTrue(screening.isPresent());
        Assert.assertEquals(SCREENING_ID,screening.get().getId());
        Assert.assertEquals(SHOWROOM,screening.get().getShowroom());
        Assert.assertEquals(MOVIE,screening.get().getMovie());
        Assert.assertEquals(TIME,screening.get().getTime());
        Assert.assertEquals(FORMAT,screening.get().getFormat());
        Assert.assertEquals(LANGUAGE,screening.get().getLanguage());
        Assert.assertEquals(THEATRE,screening.get().getTheatre());
        Assert.assertEquals(AVAILABILITY,screening.get().getAvailability());
    }

    @Test
    public void testFindByNonExistentId() {
        final Optional<Screening> screening = screeningDao.getById(NONEXISTENTSCREENING_ID);
        Assert.assertFalse(screening.isPresent());
    }

    @Test
    public void testDeleteExistingScreening() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening");
        int rowsAffected = screeningDao.delete(SCREENING_ID);
        Assert.assertEquals(1,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening");
        Assert.assertEquals(before,after+1);
    }

    @Test
    public void testDeleteNonExistingScreening() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening");
        int rowsAffected = screeningDao.delete(NONEXISTENTSCREENING_ID);
        Assert.assertEquals(0,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening");
        Assert.assertEquals(before,after);
    }

    @Test
    public void testGetByMovieAndTheatre() {
        List<Screening> screenings = screeningDao.getByMovieAndTheatre(MOVIE,THEATRE);
        Assert.assertEquals(2,screenings.size());
        Assert.assertEquals(MOVIE,screenings.get(0).getMovie());
        Assert.assertEquals(THEATRE,screenings.get(0).getTheatre());
    }

}
