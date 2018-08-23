package ar.edu.itba.paw2018b.persistence;

import ar.edu.itba.paw2018b.interfaces.FoodDao;
import ar.edu.itba.paw2018b.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDaoImpl implements FoodDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    @Autowired
    public FoodDaoImpl(final DataSource dataSource){
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcInsert = new SimpleJdbcInsert(dataSource)
                    .withSchemaName("public")
                    .withTableName("\"Food\"")
                    .usingColumns("\"FoodID\"","\"Name\"","\"Price\"","\"Stock\"");
    }

    private static final RowMapper<Food> ROW_MAPPER = new RowMapper<Food>() {
        @Override
        public Food mapRow(ResultSet resultSet, int i) throws SQLException {
            String id =  resultSet.getString("FoodID");
            String name =  resultSet.getString("Name");
            int price = resultSet.getInt("Price");
            int stock = resultSet.getInt("Stock");
            return new Food(id,name,price,stock);
        }
    };
    @Override
    public Food findById(String id){
        List<Food> f = jdbcTemplate.query("select * from \"Food\" where \"FoodID\" = ? ",ROW_MAPPER,id);
        if(f.size()!=1)
            return null;
        return f.get(0);
    }

    @Override
    public List<Food> getAll() {
        List<Food> list = jdbcTemplate.query("select * from \"Food\"", ROW_MAPPER);
        return list;
    }

    @Override
    public void insert(String id, String name, int price, int stock) {
        final Map<String, Object> entry = new HashMap<>();

        entry.put("\"FoodID\"", id);
        entry.put("\"Name\"", name);
        entry.put("\"Price\"", price);
        entry.put("\"Stock\"", stock);
        jdbcInsert.execute(entry);

    }

    @Override
    public void delete(String id) {
        if(id == null)
            return;
        jdbcTemplate.update("DELETE FROM \"Food\" WHERE \"FoodID\" = ?", id);
    }
}
