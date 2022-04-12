package com.mendes.controller;

import com.mendes.model.dto.UserDto;
import com.mendes.security.JwtAuthenticationFilter;
import com.mendes.security.JwtTokenUtil;
import com.mendes.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * @author mendes
 */

@RestController
@RequestMapping("user")
@Api(value = "user")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public UserController(UserServiceImpl userServiceImpl, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userServiceImpl = userServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @ApiOperation(value = "save")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody UserDto model) {
        return ResponseEntity.ok(userServiceImpl.save(model));
    }

    @ApiOperation(value = "token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody UserDto model) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            UserDto user = userServiceImpl.findByUsername(model.getUsername());
            String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(JwtAuthenticationFilter.TOKEN_PREFIX + token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
