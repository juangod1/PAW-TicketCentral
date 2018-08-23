package ar.edu.itba.paw2018b.models;

import java.sql.Time;
import java.sql.Timestamp;

public class Transaction {
    private int id;
    private String user;
    private Timestamp date;
    private int screeningId;
    private String seat;
    private double price;
    private boolean paid;

    public Transaction(int id, String user, int screeningId, String seat, double price,Timestamp date, boolean paid){
        this.id = id;
        this.user = user;
        this.date = date;
        this.screeningId = screeningId;
        this.seat = seat;
        this.price = price;
        this.paid = paid;
    }


    public int getId() {
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

    public String getUser() {
        return user;
    }

    public boolean isPaid() {
        return paid;
    }
}
