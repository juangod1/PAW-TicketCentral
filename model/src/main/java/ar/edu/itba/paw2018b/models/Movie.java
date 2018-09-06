package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Movie {
    //private byte[] img;
    private long id;
    private String name;
    private float rating;
    private Date releaseDate;
    private int runtime;
    private String genres;



    public Movie(long id, String name, float rating, Date releaseDate, int runtime, String genres, byte[] img){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
    //    this.img = img;
    }

    public Movie(String name, float rating, Date releaseDate, int runtime, String genres, byte[] img){
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
      //  this.img = img;
    }

    public long getId() {
        return id;
    }
    public String printMovie()
    {
        return this.name + ": " + this.rating;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
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

   /* public byte[] getImg() {
        ByteArrayInputStream bis = new ByteArrayInputStream(img);
        Image image = null;
        try {
            image = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }*/

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}
