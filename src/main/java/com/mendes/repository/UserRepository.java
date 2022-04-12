package com.mendes.repository;

import com.mendes.model.entity.User;
import com.mendes.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mendes
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT NEW com.mendes.model.dto.UserDto(u.id, u.name, u.username, u.password) FROM User u WHERE u.username = :username")
    UserDto findByUsername(@Param("username") String username);
}
