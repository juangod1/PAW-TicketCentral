package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.Showroom;
import ar.edu.itba.paw2018b.persistence.ShowroomDaoImpl;
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
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:ShowroomTestScript.sql")
public class TestShowroomDao {

    private static final String DTHEATRE_NAME = "DELETETHEATRE";
    private static final String DSHOWROOM_NAME = "DELETESHOWROOM" ;
    private String THEATRE = "ATLAS NORTE";
    private String SHOWROOM = "ATLAS 1";
    private int CAPACITY = 112;
    private String LAYOUT = "00111100n00111100n00111100n00111100n";



    @Autowired
    private ShowroomDaoImpl showroomDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Showrooms");
    }

    @Test
    public void testCreateShowroom(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Showrooms");
        final Showroom showroom = showroomDao.create(THEATRE,SHOWROOM,CAPACITY,LAYOUT);
        Assert.assertNotNull(showroom);
        Assert.assertEquals(THEATRE,showroom.getTheatre());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Showrooms"));
    }

    @Test
    public void testDeleteExistingShowroom() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Showrooms");
        int rowsAffected = showroomDao.delete(THEATRE,SHOWROOM);
        Assert.assertEquals(1,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Showrooms");
        Assert.assertEquals(before,after+1);
    }

    @Test
    public void testDeleteNonExistingShowroom() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Showrooms");
        int rowsAffected = showroomDao.delete(DTHEATRE_NAME, DSHOWROOM_NAME);
        Assert.assertEquals(0,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Showrooms");
        Assert.assertEquals(before,after);
    }

    @Test
    public void testGetShowroom(){
        final Optional<Showroom> showroom = showroomDao.getShowroom(THEATRE,SHOWROOM);
        Assert.assertTrue(showroom.isPresent());
        Assert.assertEquals(THEATRE,showroom.get().getTheatre());
        Assert.assertEquals(SHOWROOM,showroom.get().getShowroom());
        Assert.assertEquals(LAYOUT,showroom.get().getLayout());
        Assert.assertEquals(CAPACITY,showroom.get().getCapacity());
    }
}
