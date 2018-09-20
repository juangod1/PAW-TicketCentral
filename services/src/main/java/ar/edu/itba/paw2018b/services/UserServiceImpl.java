package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.UserDao;
import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User create(String dni, String name, String surname, String username, String pass, String  phone, String email) throws EmailExistsException {

        Optional<User> user = userDao.findByEmail(email);
        if(user.isPresent()) throw new EmailExistsException();

        return userDao.create(dni,name,surname,username,pass,phone,email,false);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
