package ar.edu.itba.paw2018b.models;

public class Seat {
    String name;
    int coordx;
    int coordy;
    boolean occupied;

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
}
