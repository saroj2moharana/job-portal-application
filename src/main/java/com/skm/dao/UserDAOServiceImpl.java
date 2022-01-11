package com.skm.dao;

import com.skm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Saroj on 11/01/22
 **/
@Service
public class UserDAOServiceImpl {
    @Autowired
    UserRepository userRepository;

    public void storeUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isEmpty()?null:userOptional.get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
