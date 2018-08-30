package ar.edu.itba.paw2018b.models;

public class Movie {
    private String id;
    private String name;
    private float rating;
    private int year;
    private int runtime;
    private String genres;
    private boolean premiere;


    public Movie(String id, String name, float rating, int year, int runtime, String genres, boolean premiere){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.premiere = premiere;
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

    public int getYear() {
        return year;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
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

    public boolean getPremiere() {
        return premiere;
    }
}
