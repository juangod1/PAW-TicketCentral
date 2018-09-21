package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.dao.FoodDao;
import ar.edu.itba.paw2018b.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.*;
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
                    .withTableName("Food")
                    .usingGeneratedKeyColumns("foodid")
                    .usingColumns("FoodName","Price","Stock","Image");
    }

private static final RowMapper<Food> ROW_MAPPER =  (rs, i) ->
        new Food(rs.getInt("FoodID"),rs.getString("FoodName"),rs.getInt("Price"),rs.getInt("Stock"),rs.getBytes("Image"));


    @Override
    public Optional<Food> findById(int id){
        return jdbcTemplate.query("select * from Food where FoodID = ? ",ROW_MAPPER,id)
                .stream().findFirst();
    }


    @Override
    public List<Food> getAll() {
        List<Food> list = jdbcTemplate.query("select * from Food", ROW_MAPPER);
        return list;
    }

    @Override
    public Food create(String name, int price, int stock, byte[] img) {
        final Map<String, Object> entry = new HashMap<>();
        entry.put("FoodName", name);
        entry.put("Price", price);
        entry.put("Stock", stock);
        entry.put("Image", img);
        Number id = jdbcInsert.executeAndReturnKey(entry);
        return new Food(id.intValue(),name,price,stock,img);
    }

    @Override
    public int delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM Food WHERE FoodID = ?", id);
        return rows;
    }
}
