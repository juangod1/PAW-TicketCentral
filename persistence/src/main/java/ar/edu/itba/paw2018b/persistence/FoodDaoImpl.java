package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.FoodDao;
import ar.edu.itba.paw2018b.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class FoodDaoImpl implements FoodDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public FoodDaoImpl(final DataSource dataSource){
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcInsert = new SimpleJdbcInsert(dataSource)
                    .withSchemaName("public")
                    .withTableName("Food")
                    .usingColumns("FoodID","Name","Price","Stock","Image");
    }

private static final RowMapper<Food> ROW_MAPPER =  (rs, i) ->
        new Food(rs.getString("FoodID"),rs.getString("Name"),rs.getInt("Price"),rs.getInt("Stock"),rs.getBytes("Image"));


    @Override
    public Optional<Food> findById(String id){
        return jdbcTemplate.query("select * from Food where FoodID = ? ",ROW_MAPPER,id)
                .stream().findFirst();
    }


    @Override
    public List<Food> getAll() {
        List<Food> list = jdbcTemplate.query("select * from Food", ROW_MAPPER);
        return list;
    }

    @Override
    public Food create(String id, String name, int price, int stock, byte[] img) {
        final Map<String, Object> entry = new HashMap<>();
        entry.put("FoodID", id);
        entry.put("Name", name);
        entry.put("Price", price);
        entry.put("Stock", stock);
        entry.put("Image", img);
        jdbcInsert.execute(entry);
        return new Food(id,name,price,stock,img);
    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("DELETE FROM Food WHERE FoodID = ?", id);
    }
}
