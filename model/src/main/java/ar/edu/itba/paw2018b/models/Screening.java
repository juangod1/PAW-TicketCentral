package ar.edu.itba.paw2018b.models;

import java.sql.Timestamp;

public class Screening {
    private int id;
    private String showroom;
    private String theatre;
    private long movie;
    private Timestamp time;
    private String format;
    private String language;
    private int availability;

    public Screening(int id, String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability){
        this.id=id;
        this.showroom=showroom;
        this.theatre=theatre;
        this.movie=movie;
        this.time=time;
        this.format=format;
        this.language=language;
        this.availability = availability;
    }
    public Screening(String showroom, long movie, Timestamp time, String format, String language, String theatre, int availability){
        this.showroom=showroom;
        this.theatre=theatre;
        this.movie=movie;
        this.time=time;
        this.format=format;
        this.language=language;
        this.availability = availability;
    }

    public void printScreening(){
        System.out.println(id + ": " + showroom + ": " + theatre + ": " + movie
        + ": " + time + ": " + format + ": " + language);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
