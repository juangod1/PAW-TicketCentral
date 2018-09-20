package ar.edu.itba.paw2018b.interfaces.dao;

import ar.edu.itba.paw2018b.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAll();

    Optional<User> findByDni(String dni);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    User create(String dni, String name, String surname, String username, String password, String phone, String email, boolean isAdmin);

    int delete(long id);
}
