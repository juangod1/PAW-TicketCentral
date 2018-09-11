package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
