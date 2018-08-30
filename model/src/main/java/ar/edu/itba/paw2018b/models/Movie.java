package ar.edu.itba.paw2018b.models;


import java.sql.Date;

public class Movie {
    private byte[] img;
    private String id;
    private String name;
    private float rating;
    private Date releaseDate;
    private int runtime;
    private String genres;



    public Movie(String id, String name, float rating, Date releaseDate, int runtime, String genres, byte[] img){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
        this.img = img;
    }

    public String getId() {
        return id;
    }
    public String printMovie()
    {
        return this.name + ": " + this.rating;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getGenres() {
        return genres;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

}
