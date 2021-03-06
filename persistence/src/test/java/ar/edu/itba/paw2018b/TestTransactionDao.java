package ar.edu.itba.paw2018b;

import ar.edu.itba.paw2018b.models.Transaction;
import ar.edu.itba.paw2018b.persistence.TransactionDaoImpl;
import org.hsqldb.jdbc.JDBCUtil;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:TransactionTestScript.sql")
@Transactional
public class TestTransactionDao {

    private static final int TRANSACTION_ID = 2;
    private static final int TRANSACTION_USER = 1;
    private static final int TRANSACTION_SCREENINGID = 3;
    private static final String TRANSACTION_SEATS = "A1";
    private static final String TRANSACTION_FOOD = null;
    private static final double TRANSACTION_PRICE = 400;
    private static final Timestamp TRANSACTION_DATE = Timestamp.valueOf("2018-09-20 16:47:20");
    private static final boolean TRANSACTION_PAID = false;
    @Autowired
    private TransactionDaoImpl transactionDao;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testCreateTransaction(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Transactions");
        final Transaction transaction = transactionDao.create(TRANSACTION_USER,TRANSACTION_SCREENINGID,TRANSACTION_SEATS,TRANSACTION_FOOD,TRANSACTION_PRICE,TRANSACTION_PAID,TRANSACTION_DATE);
        Assert.assertNotNull(transaction);
        Assert.assertEquals(TRANSACTION_USER,transaction.getUserId());
        Assert.assertEquals(TRANSACTION_SEATS,transaction.getSeat());
        Assert.assertEquals(TRANSACTION_SCREENINGID, transaction.getScreeningId());
        Assert.assertEquals(TRANSACTION_DATE, transaction.getDate());
    }

    @Test
    public void testGetTransactionById(){
        final Optional<Transaction> transaction = transactionDao.getTransactionById(TRANSACTION_ID);

        Assert.assertTrue(transaction.isPresent());
        Assert.assertEquals(TRANSACTION_ID,transaction.get().getId().longValue());
    }

    @Test
    public void testGetTransactionsByUser(){
        final List<Transaction> transactions = transactionDao.getTransactionsByUserId(TRANSACTION_USER);

        Assert.assertEquals(1,transactions.size());
        Assert.assertEquals(TRANSACTION_USER,transactions.get(0).getUserId());
    }

    @Test
    public void testGetTransactionsByScreening(){
        final List<Transaction> transactions = transactionDao.getTransactionsByScreening(TRANSACTION_SCREENINGID);

        Assert.assertEquals(1,transactions.size());
        Assert.assertEquals(TRANSACTION_SCREENINGID,transactions.get(0).getScreeningId());
    }

    @Test
    public void testFindUserHistory(){
        final List<Transaction> transactions = transactionDao.getTransactionsByUserId(TRANSACTION_USER);
        Assert.assertEquals(1,transactions.size());
        Assert.assertEquals(TRANSACTION_ID,transactions.get(0).getId().longValue());
    }

    @Test
    public void testTransformIntoBuy(){
        int rows = transactionDao.transformIntoBuy(TRANSACTION_ID);
        Assert.assertEquals(1,rows);
    }

}
