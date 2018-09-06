package ar.edu.itba.paw2018b.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Theatre {
    private String name;
    private String address;
    private String city;

    public Theatre(String name, String address, String city){
        this.name=name;
        this.address=address;
        this.city=city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }


}
