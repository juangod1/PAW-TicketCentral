package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class User {
    private String dni;
    private String name;
    private String surname;
    private String mobile;
    private String email;

    public User(){}

    public User(String dni, String name, String surname, String mobile, String email) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSurname() {
        return surname;
    }
}
