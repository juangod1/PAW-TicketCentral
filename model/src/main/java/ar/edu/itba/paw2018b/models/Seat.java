package ar.edu.itba.paw2018b.models;

public class Seat {
    int coordx;
    int coordy;
    boolean occupied;

    public Seat(int coordx, int coordy, boolean occupied){
        this.coordx=coordx;
        this.coordy=coordy;
        this.occupied=occupied;
    }
}
