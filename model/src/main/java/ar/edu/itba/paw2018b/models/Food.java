package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Food {
    private byte[] img;
    private int id;
    private String name;
    private int price;
    private int stock;

    public Food(){}

    public Food(int id, String name, int price, int stock, byte[] img){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
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
