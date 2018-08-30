package ar.edu.itba.paw2018b.models;

public class Theatre {
    private String name;
    private String address;
    private String city;

    public Theatre(String name, String address, String city){
        this.name=name;
        this.address=address;
        this.city=city;
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
