package com.tsragravorogh.PhotoService.service;

import com.tsragravorogh.PhotoService.model.User;
import com.tsragravorogh.PhotoService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findOneUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(User user) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (user.equals(userFromDb)) {
            return userFromDb;
        } else {
            return null;
        }
    }
}
