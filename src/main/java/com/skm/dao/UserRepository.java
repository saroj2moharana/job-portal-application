package com.skm.dao;

import com.skm.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Saroj on 11/01/22
 **/
@Service
public class UserRepository {
    private static Map<String,User> userMap = new HashMap<>();
    Optional<User> findByUsername(String username){
        return userMap.values().parallelStream().filter(m -> m.getUsername().equalsIgnoreCase(username)).findAny();
    }

    void save(User user){
        userMap.put(user.getEmail(),user);
    }

    public User findByEmail(String email) {
        return userMap.get(email);
    }
}
