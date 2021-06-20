package com.mendes.service;

import com.mendes.model.User;
import com.mendes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author mendes
 */

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User save(User model){
        model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        User user = userRepository.save(model);
        return user;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
