package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class AnotherUserServiceImplementation implements UserServiceInterface {
    @Override
    public User findbyID() {
        return null;
    }
}
