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
import java.io.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:FoodTestScript.sql")
public class TestFoodDao {

    private String ID = "f1";
    private String NAME = "Popcorn";
    private int PRICE  = 100;
    private int STOCK = 1000;
    private File IMAGE = new File("C:\\Users\\cderienzo\\Documents\\ITBA\\PAW-TicketCentral\\persistence\\src\\test\\resources\\popcorn.jpg");
    private byte[] BYTES;

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
        final Food food = foodDao.create(ID,NAME,PRICE,STOCK,BYTES);
        Assert.assertNotNull(food);
        Assert.assertEquals(ID,food.getId());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate,"Food"));
    }


}
