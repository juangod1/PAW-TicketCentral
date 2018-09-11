package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class User {
    private final String dni;
    private final String name;
    private final String surname;
    private final String username;
    private final String password;
    private String mobile;
    private String email;

    public User(String dni, String name, String surname, String username, String password){
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User(final String dni, final String name, final String surname, final String username, final String password, String mobile, String email) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
