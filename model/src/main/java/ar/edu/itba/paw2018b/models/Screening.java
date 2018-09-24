package ar.edu.itba.paw2018b.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Timestamp;


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Screening {
    private long id;
    private String showroom;
    private String theatre;
    private long movie;
    private Timestamp time;
    private String format;
    private String language;
    private int availability;
    private int price;

    public Screening(){}

    public Screening(long id, String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability, int price){
        this.id=id;
        this.showroom=showroom;
        this.theatre=theatre;
        this.movie=movie;
        this.time=time;
        this.format=format;
        this.language=language;
        this.availability = availability;
        this.price = price;
    }

    public void printScreening(){
        System.out.println(id + ": " + showroom + ": " + theatre + ": " + movie
        + ": " + time + ": " + format + ": " + language);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public String getLanguage() {
        return language;
    }

    public long getMovie() {
        return movie;
    }

    public String getShowroom() {
        return showroom;
    }

    public String getTheatre() {
        return theatre;
    }

    public Timestamp getTime() {
        return time;
    }


    public void setShowroom(String showroom) {
        this.showroom = showroom;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public void setMovie(long movie) {
        this.movie = movie;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAvailability() {
        return availability;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
