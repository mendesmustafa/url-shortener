package com.mendes.service;

import com.mendes.model.dto.UserDto;
import com.mendes.repository.UserRepository;
import com.mendes.service.user.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mendes
 */

@SpringBootTest
class UserServiceImplTest {

    private static final String NAME = "TEST-NAME";
    private static final String USERNAME = "TEST-USERNAME";
    private static final String PASSWORD = "TEST-PASSWORD";

    UserDto defaultUserDto;
    UserDto resultUserDto;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @BeforeEach
    public void setUp() {
        defaultUserDto = new UserDto();
        defaultUserDto.setName(NAME);
        defaultUserDto.setUsername(USERNAME);
        defaultUserDto.setPassword(bCryptPasswordEncoder.encode(PASSWORD));
    }

    @AfterEach
    public void clear() {
        if (resultUserDto != null && resultUserDto.getId() != null) {
            userRepository.deleteById(resultUserDto.getId());
        }
    }

    @Test
    void save() {
        resultUserDto = userServiceImpl.save(defaultUserDto);
        assertEquals(1, resultUserDto.getId());
    }

    @Test
    void findByUsername() {
        resultUserDto = userServiceImpl.save(defaultUserDto);
        UserDto user = userServiceImpl.findByUsername(resultUserDto.getUsername());
        assertEquals(USERNAME, user.getUsername());
    }
}