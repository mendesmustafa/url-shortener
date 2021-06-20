package com.mendes.service;

import com.mendes.model.User;
import com.mendes.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mendes
 */

@SpringBootTest
class UserServiceTest {

    private static final String NAME = "MENDES";
    private static final String USERNAME = "MENDES";
    private static final String PASSWORD = "MENDES";

    User defaultUser;
    User resultUser;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        defaultUser = new User();
        defaultUser.setName(NAME);
        defaultUser.setUsername(USERNAME);
        defaultUser.setPassword(PASSWORD);
    }

    @AfterEach
    public void clear() {
        if (resultUser != null && resultUser.getId() != null) {
            userRepository.deleteById(resultUser.getId());
        }
    }

    @Test
    public void save() {
        resultUser = userService.save(defaultUser);
        assertNotNull(resultUser.getId());
    }

    @Test
    public void findByUsername() {
        resultUser = userService.save(defaultUser);
        User user = userService.findByUsername(resultUser.getUsername());
        assertEquals(user.getUsername(), USERNAME);
    }
}