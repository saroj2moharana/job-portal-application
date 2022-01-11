package com.skm.service;

import com.skm.dao.UserDAOServiceImpl;
import com.skm.dao.UserRepository;
import com.skm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Saroj on 11/01/22
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDAOServiceImpl userDAOService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAOService.getUserByUsername(s);
        if(user == null) new UsernameNotFoundException("User Not Found with username: " + s);

        return UserDetailsImpl.build(user);
    }
}
