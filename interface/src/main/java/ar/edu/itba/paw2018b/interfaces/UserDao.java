package ar.edu.itba.paw2018b.interfaces;

import ar.edu.itba.paw2018b.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void insert(String dni, String name, String surname, String phone, String email);

    void delete(String dni);

}
