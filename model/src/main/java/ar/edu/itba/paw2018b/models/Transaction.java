package ar.edu.itba.paw2018b.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Time;
import java.sql.Timestamp;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Transaction {
    private Integer id;
    private int userId;
    private Timestamp date;
    private int screeningId;
    private String seat;
    private String food;
    private double price;
    private boolean paid;

    public Transaction(){}


    public Transaction(int id, int userId, int screeningId, String seat, String food, double price, Timestamp date, boolean paid){
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.screeningId = screeningId;
        this.seat = seat;
        this.food=food;
        this.price = price;
        this.paid = paid;
    }


    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public String getSeat() {
        return seat;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPaid() {
        return paid;
    }
    public Timestamp getDate() {
        return date;
    }

    public String getFood() {
        return food;
    }

}
