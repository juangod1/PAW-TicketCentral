package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.Theatre;
import ar.edu.itba.paw2018b.persistence.TheatreDaoImpl;
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
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:TheatreTestScript.sql")
@Transactional
public class TestTheatreDao {

    private static final String THEATRE_NAME = "ATLAS TEST" ;
    private static final String THEATRE_ADDRESS = "Test 100";
    private static final String THEATRE_CITY = "TestCity";
    private static final String NONEXISTENTTHEATRE_NAME = "NON EXISTENT";
    @Autowired
    private TheatreDaoImpl theatreDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testCreateTheatre() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Theatre");
        final Theatre theatre = theatreDao.create(THEATRE_NAME,THEATRE_ADDRESS,THEATRE_CITY);
        Assert.assertNotNull(theatre);
        Assert.assertEquals(THEATRE_NAME,theatre.getName());
        Assert.assertEquals(THEATRE_ADDRESS,theatre.getAddress());
        Assert.assertEquals(THEATRE_CITY,theatre.getCity());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Theatre"));
    }

    @Test
    public void testFindByName() {
        final Optional<Theatre> theatre = theatreDao.getTheatreByName(THEATRE_NAME);
        Assert.assertTrue(theatre.isPresent());
        Assert.assertEquals(THEATRE_NAME,theatre.get().getName());
        Assert.assertEquals(THEATRE_ADDRESS,theatre.get().getAddress());
        Assert.assertEquals(THEATRE_CITY,theatre.get().getCity());
    }

    @Test
    public void testFindByNonExistentName() {
        final Optional<Theatre> theatre = theatreDao.getTheatreByName(NONEXISTENTTHEATRE_NAME);
        Assert.assertFalse(theatre.isPresent());
    }

    @Test
    public void testDeleteExistingTheatre() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Theatre");
        int rowsAffected = theatreDao.delete(THEATRE_NAME);
        Assert.assertEquals(1,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Theatre");
        Assert.assertEquals(before,after+1);
    }

    @Test
    public void testDeleteNonExistingTheatre() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Theatre");
        int rowsAffected = theatreDao.delete(NONEXISTENTTHEATRE_NAME);
        Assert.assertEquals(0,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Theatre");
        Assert.assertEquals(before,after);
    }
}
