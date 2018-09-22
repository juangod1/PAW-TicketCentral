package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.EmailExistsException;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;

import java.util.Optional;

public interface UserService {
    User create(String dni, String name, String surname, String username, String pass, String phone, String email) throws EmailExistsException;

    User findByUsername(String username);

    User findById(int userId) throws NotFoundException;
}
