package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import com.heritage.bibandcineapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<Page<User>>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Create new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User successfully created an account",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        return new ResponseEntity<User>(userService.register(user), HttpStatus.OK);
    }


    @Operation(summary = "Get user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User details fetched successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId,
                                           @RequestBody @Valid User userRequest) {
        return new ResponseEntity<User>(userService.update(userId, userRequest), HttpStatus.OK);
    }
}