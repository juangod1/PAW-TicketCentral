package ar.edu.itba.paw2018b;


import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.persistence.FoodDaoImpl;
import org.hsqldb.jdbc.JDBCUtil;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:FoodTestScript.sql")
public class TestFoodDao {

    private String ID = "f1";
    private String NAME = "Popcorn";
    private int PRICE  = 100;
    private int STOCK = 1000;

    @Autowired
    private FoodDaoImpl foodDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testCreateFood(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Food");
        final Food food = foodDao.create(ID,NAME,PRICE,STOCK);
        Assert.assertNotNull(food);
        Assert.assertEquals(ID,food.getId());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food"));
    }


}
