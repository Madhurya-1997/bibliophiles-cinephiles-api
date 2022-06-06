package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import com.heritage.bibandcineapi.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/users")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<Page<User>>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    /**
     * Registration of a user
     */
    @PostMapping("api/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        return new ResponseEntity<User>(userService.register(user), HttpStatus.OK);
    }

    @PutMapping("api/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId,
                                           @RequestBody @Valid User userRequest) {
        return new ResponseEntity<User>(userService.update(userId, userRequest), HttpStatus.OK);
    }
}