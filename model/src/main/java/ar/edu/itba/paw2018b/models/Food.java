package ar.edu.itba.paw2018b.models;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

public class Food {
    private Blob img;
    private String id;
    private String name;
    private int price;
    private int stock;

    public Food(String id, String name, int price, int stock, Blob img){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
