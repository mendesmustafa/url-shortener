package com.mendes.service.user;

import com.mendes.model.dto.UserDto;

/**
 * @author mendesmustafa on 22-03-2022
 */

public interface UserService {

    UserDto save(UserDto model);

    UserDto findByUsername(String username);
}
