package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.persistence.ScreeningDaoImpl;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:ScreeningTestScript.sql")
public class TestScreeningDao {
    private static final String SHOWROOM = "ATLAS 1";
    private static final long MOVIE = 1;
    private static final Timestamp TIME = new Timestamp(System.currentTimeMillis());
    private static final String FORMAT = "2D";
    private static final String LANGUAGE = "SUBTITULADO";
    private static final String THEATRE = "ATLAS NORTE";
    private static final int AVAILABILITY = 100;

    @Autowired
    private ScreeningDaoImpl screeningDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testCreateScreening(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Screening");
        final Screening screening = screeningDao.create(SHOWROOM,MOVIE,TIME,FORMAT,LANGUAGE,THEATRE,AVAILABILITY);
        Assert.assertNotNull(screening);
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Screening"));
    }

}
