package com.tsragravorogh.PRBD.service;

import com.tsragravorogh.PRBD.controller.UsersController;
import com.tsragravorogh.PRBD.model.User;
import com.tsragravorogh.PRBD.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static User user;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String surname, String email, String password) {
        if (email == null || password == null) {
            logger.info("User or password is null");
            return null;
        } else {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            logger.info("User are saving into repo {}", user.toString() );
            return userRepository.save(user);
        }
    }

    public User authenticate(String email, String password) {
        logger.info("User is loging {}", email);
        User userFromDb = userRepository.findByEmailAndPassword(email, password).orElse(null);
        setUser(userFromDb);
        return userFromDb;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserService.user = user;
    }


}
