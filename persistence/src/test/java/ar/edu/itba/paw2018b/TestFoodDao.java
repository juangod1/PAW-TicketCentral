package ar.edu.itba.paw2018b;


import ar.edu.itba.paw2018b.models.Food;
import ar.edu.itba.paw2018b.persistence.FoodDaoImpl;
import org.hsqldb.jdbc.JDBCUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:FoodTestScript.sql")
@Transactional
public class TestFoodDao {


    private final File IMAGE = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\test\\resources\\popcorn.jpg");
    private byte[] BYTES;
    private static final int FOOD_ID = 2;
    private static final String FOOD_NAME = "Popcorn";
    private static final int FOOD_PRICE = 100;
    private static final int FOOD_STOCK = 1000;
    private static final int DELETEFOOD_ID = 3;
    private static final int NONEXISTENTFOOD_ID = 4;


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
        final Food food = foodDao.create(FOOD_NAME,FOOD_PRICE,FOOD_STOCK,BYTES);
        Assert.assertNotNull(food);
        Assert.assertEquals(FOOD_NAME,food.getName());
        Assert.assertEquals(FOOD_STOCK,food.getStock());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food"));
    }

    @Test
    public void testFindById(){
        final Optional<Food> food =	foodDao.findById(FOOD_ID);
        Assert.assertTrue(food.isPresent());
        Assert.assertEquals(FOOD_ID,food.get().getId());
        Assert.assertEquals(FOOD_NAME,food.get().getName());
        Assert.assertEquals(FOOD_PRICE,food.get().getPrice());
        Assert.assertEquals(FOOD_STOCK,food.get().getStock());
    }

    @Test
    public void testFindByNonExistentId(){
        final Optional<Food> food =	foodDao.findById(NONEXISTENTFOOD_ID);
        Assert.assertFalse(food.isPresent());
    }

    @Test
    public void testGetAll() {
        List<Food> foods = foodDao.getAll();
        Assert.assertEquals(foods.size(),JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food"));;
    }

    @Test
    public void testDeleteExistingFood() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food");
        int rowsAffected = foodDao.delete(DELETEFOOD_ID);
        Assert.assertEquals(1,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food");
        Assert.assertEquals(before,after+1);
    }

    @Test
    public void testDeleteNonExistingFood() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food");
        int rowsAffected = foodDao.delete(NONEXISTENTFOOD_ID);
        Assert.assertEquals(0,rowsAffected);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food");
        Assert.assertEquals(before,after);
    }
}
