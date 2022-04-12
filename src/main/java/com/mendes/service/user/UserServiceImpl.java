package com.mendes.service.user;

import com.mendes.model.entity.User;
import com.mendes.model.dto.UserDto;
import com.mendes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author mendes
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto save(UserDto model) {
        User user = new User();
        user.setName(model.getName());
        user.setUsername(model.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        userRepository.save(user);
        model.setId(user.getId());
        return model;
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
