package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.persistence.UserDaoImpl;
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
@Sql("classpath:UsersTestScript.sql")
@Transactional
public class TestUserDao {

    private static final long USER_ID = 1;
    private static final String USER_DNI = "12345678";
    private static final String USER_NAME = "TESTNAME";
    private static final String USER_SURNAME = "TESTSURNAME";
    private static final String USER_PASSWORD = "TESTPASSWORD";
    private static final String USER_PHONE = "1522334455";
    private static final String USER_EMAIL = "test@mail.com";
    private static final boolean USER_ADMIN = true;
    private static final String USER_USERNAME = "TESTUSERNAME";
    private static final long NONEXISTENTUSER_ID= 3;
    private static final String NONEXISTENTUSER_USERNAME = "NON EXISTENT";

    @Autowired
    private UserDaoImpl userDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testCreateTheatre() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Users");
        final User user = userDao.create(USER_DNI,USER_NAME,USER_SURNAME,USER_USERNAME,USER_PASSWORD,USER_PHONE,USER_EMAIL,USER_ADMIN);
        Assert.assertNotNull(user);
        Assert.assertEquals(USER_NAME,user.getName());
        Assert.assertEquals(USER_DNI,user.getDni());
        Assert.assertEquals(USER_SURNAME,user.getSurname());
        Assert.assertEquals(USER_PASSWORD,user.getPassword());
        Assert.assertEquals(USER_PHONE,user.getMobile());
        Assert.assertEquals(USER_EMAIL,user.getEmail());
        Assert.assertEquals(USER_ADMIN,user.isAdmin());
        Assert.assertEquals(USER_USERNAME,user.getUsername());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Users"));
    }

    @Test
    public void testFindByDni() {
        final Optional<User> user = userDao.findById(USER_ID);
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(USER_DNI,user.get().getDni());
        Assert.assertEquals(USER_NAME,user.get().getName());
        Assert.assertEquals(USER_SURNAME,user.get().getSurname());
        Assert.assertEquals(USER_USERNAME,user.get().getUsername());
        Assert.assertEquals(USER_PASSWORD,user.get().getPassword());
        Assert.assertEquals(USER_PHONE,user.get().getMobile());
        Assert.assertEquals(USER_EMAIL,user.get().getEmail());
        Assert.assertEquals(USER_ADMIN,user.get().isAdmin());
    }

    @Test
    public void testFindByNonExistentId() {
        final Optional<User> user = userDao.findById(NONEXISTENTUSER_ID);
        Assert.assertFalse(user.isPresent());
    }
    public void testFindByUsername() {
        final Optional<User> user = userDao.findByUsername(USER_USERNAME);
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(USER_DNI,user.get().getDni());
        Assert.assertEquals(USER_NAME,user.get().getName());
        Assert.assertEquals(USER_SURNAME,user.get().getSurname());
        Assert.assertEquals(USER_USERNAME,user.get().getUsername());
        Assert.assertEquals(USER_PASSWORD,user.get().getPassword());
        Assert.assertEquals(USER_PHONE,user.get().getMobile());
        Assert.assertEquals(USER_EMAIL,user.get().getEmail());
        Assert.assertEquals(USER_ADMIN,user.get().isAdmin());
    }

    @Test
    public void testFindByNonExistentUsername() {
        final Optional<User> user = userDao.findByDni(NONEXISTENTUSER_USERNAME);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testDeleteUser() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Users");
        int rowsAffected = userDao.delete(USER_ID);
        Assert.assertEquals(1,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Users");
        Assert.assertEquals(before,after+1);
    }

    @Test
    public void testDeleteNonExistingFood() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Users");
        int rowsAffected = userDao.delete(NONEXISTENTUSER_ID);
        Assert.assertEquals(0,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Users");
        Assert.assertEquals(before,after);
    }
}
