package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAll();

    Optional<User> findById(long id);

    User create(String dni, String name, String surname, String username, String password, String phone, String email);

    Optional<User> findByUsername(String username);

    void delete(String dni);

}
