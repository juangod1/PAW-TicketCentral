package ar.edu.itba.paw2018b;


import ar.edu.itba.paw2018b.persistence.FoodDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class TestFoodDao {

    @Autowired
    private FoodDaoImpl foodDao;

    @Test
    @Transactional

    public void testCreateFood(){
//       foodDao.create();
    }


}
