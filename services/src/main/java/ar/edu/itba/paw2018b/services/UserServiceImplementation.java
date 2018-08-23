package ar.edu.itba.paw2018b.services;


import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImplementation implements UserServiceInterface {
    @Override
    public User findbyID() {
        return null;
    }
}
