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
    public void setUpFood(){
        jdbcTemplate.update("delete from Food");
        final Map<String, Object> entry = new HashMap<>();
        entry.put("FoodName", "Popcorn");
        entry.put("Price", 100);
        entry.put("Stock", 2000);
        String dir = "/home/juangod/ITBA/PAW/PAW-TicketCentral/persistence/src/main/resources/popcorn.jpg";
        File IMAGE = new File(dir);
        byte[] img = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found" + dir); }
        entry.put("Image",img);
        jdbcInsert.executeAndReturnKey(entry);

        final Map<String, Object> entry1 = new HashMap<>();
        entry1.put("FoodName", "Nachos");
        entry1.put("Price", 80);
        entry1.put("Stock", 1500);
        dir = "/home/juangod/ITBA/PAW/PAW-TicketCentral/persistence/src/main/resources/nachos.png";
        File IMAGE1 = new File(dir);
        byte[] img1 = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE1);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img1 = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found" + dir); }
        entry1.put("Image",img1);
        jdbcInsert.executeAndReturnKey(entry1);

        final Map<String, Object> entry2 = new HashMap<>();
        entry2.put("FoodName", "Diet Coke");
        entry2.put("Price", 50);
        entry2.put("Stock", 3000);
        dir = "/home/juangod/ITBA/PAW/PAW-TicketCentral/persistence/src/main/resources/dietcoke.jpg";
        File IMAGE2 = new File(dir);
        byte[] img2 = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE2);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img2 = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found"); }
        entry2.put("Image",img2);
        jdbcInsert.executeAndReturnKey(entry2);
        final Map<String, Object> entry3= new HashMap<>();
        entry3.put("FoodName", "Pizza");
        entry3.put("Price", 150);
        entry3.put("Stock", 1000);
        dir = "/home/juangod/ITBA/PAW/PAW-TicketCentral/persistence/src/main/resources/pizza.png";
        File IMAGE3 = new File(dir);
        byte[] img3 = null;
        try {
            FileInputStream fis = new FileInputStream(IMAGE3);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) { }
            img3 = bos.toByteArray();
        } catch (FileNotFoundException f) { System.out.println("File not found" + dir); }
        entry3.put("Image",img3);
        Number id = jdbcInsert.executeAndReturnKey(entry3);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Food WHERE FoodID = ?", id);
    }
}
