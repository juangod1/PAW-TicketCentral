package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.UserDao;
import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.EmailExistsException;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
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
    public User findByUsername(String username) throws NotFoundException{
        return userDao.findByUsername(username).orElseThrow(() -> new NotFoundException("No user found for username: "+ username));
    }

    @Override
    public User findById(int userId) throws NotFoundException{
        return userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user found for Id: "+ userId));
    }
}
