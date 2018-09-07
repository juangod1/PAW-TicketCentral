package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Seat {
    private String name;
    private int coordx;
    private int coordy;
    private boolean occupied;

    public Seat(){}

    public Seat(int coordx, int coordy, boolean occupied, String name){
        this.coordx=coordx;
        this.coordy=coordy;
        this.occupied=occupied;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getCoordx(){
        return coordx;
    }

    public int getCoordy(){
        return coordy;
    }

    public boolean getOccupied(){
        return occupied;
    }
}
