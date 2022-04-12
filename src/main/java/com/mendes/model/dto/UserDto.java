package com.mendes.model.dto;

import java.io.Serializable;

/**
 * @author mendesmustafa on 24-02-2022
 */

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
