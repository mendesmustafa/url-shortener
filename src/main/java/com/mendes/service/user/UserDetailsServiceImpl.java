package com.mendes.service.user;

import com.mendes.model.dto.UserInfoDetails;
import com.mendes.model.dto.UserDto;
import com.mendes.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mendes
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found : " + username);
        }
        return new UserInfoDetails(user);
    }
}
