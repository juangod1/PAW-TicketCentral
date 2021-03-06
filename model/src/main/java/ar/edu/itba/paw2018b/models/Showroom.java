package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Showroom {
    private String showroom;
    private String theatre;
    private int capacity;
    private String layout;

    public Showroom(){}

    public Showroom(String theatre, String showroom, int capacity,String layout) {
        this.showroom = showroom;
        this.theatre = theatre;
        this.capacity = capacity;
        this.layout = layout;
    }


    public String getShowroom() {
        return showroom;
    }

    public String getTheatre() {
        return theatre;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLayout(){
        return layout;
    }
}
