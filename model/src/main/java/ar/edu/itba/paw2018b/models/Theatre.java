package ar.edu.itba.paw2018b.models;

public class Theatre {
    private int id;
    private String name;
    private String address;
    private String city;

    public Theatre(int id, String name, String address, String city){
        this.id=id;
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
