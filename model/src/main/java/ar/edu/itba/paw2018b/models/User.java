package ar.edu.itba.paw2018b.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class User {
    private final long id;
    private final String dni;
    private final String name;
    private final String surname;
    private final String username;
    private final String password;
    private String mobile;
    private String email;
    private boolean isAdmin;

    public User(final long id, final String dni, final String name, final String surname, final String username, final String password, String mobile, String email, boolean isAdmin) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
