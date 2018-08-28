package ar.edu.itba.paw2018b.models;

public class Showroom {
    private String name;
    private String theatre;
    private int capacity;
    private String layout;


    public Showroom(String name, String theatre, int capacity,String layout) {
        this.name = name;
        this.theatre = theatre;
        this.capacity = capacity;
        this.layout = layout;
    }


    public String getName() {
        return name;
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