package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.dao.UserDao;
import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.DniExistsException;
import ar.edu.itba.paw2018b.models.exception.EmailExistsException;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import ar.edu.itba.paw2018b.models.exception.UserNameExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDao userDao;

    @Override
    public User create(String dni, String name, String surname, String username, String pass, String  phone, String email) throws EmailExistsException {

        Optional<User> user = userDao.findByEmail(email);
        if(user.isPresent()) throw new EmailExistsException();

        user = userDao.findByUsername(username);
        if(user.isPresent()) throw new UserNameExistsException();

        user = userDao.findByDni(dni);
        if(user.isPresent()) throw new DniExistsException();


        return userDao.create(dni,name,surname,username,pass,phone,email,false);
    }

    @Override
    public User findByUsername(String username) throws NotFoundException{
        if(username == null){
            LOGGER.error("Attempted to find a user with a null username");
            throw new IllegalArgumentException("username can't be null");
        }
        return userDao.findByUsername(username).orElseThrow(() -> new NotFoundException("No user found for username: "+ username));
    }

    @Override
    public User findById(int userId) throws NotFoundException{
        if(userId < 0){
            LOGGER.error("Attempted to find a user with a negative id");
            throw new IllegalArgumentException("id must be positive");
        }
        LOGGER.trace("looking up user with id {}", userId);
        return userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user found for Id: "+ userId));
    }
}
