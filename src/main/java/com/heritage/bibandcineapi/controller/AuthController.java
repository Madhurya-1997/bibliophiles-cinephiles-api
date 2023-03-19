package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.AuthRequest;
import com.heritage.bibandcineapi.models.AuthResponse;
import com.heritage.bibandcineapi.repository.UserRepository;
import com.heritage.bibandcineapi.security.JwtUtil;
import com.heritage.bibandcineapi.services.MyUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Operation(summary = "Login user to access all APIs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Login successful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Login unsuccessful. You are unauthorized to access our APIs.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest req) throws Exception {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    req.getUsername(), req.getPassword()
            ));
        } catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(req.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse( jwt,
                userRepository.findUserByUsername(userDetails.getUsername()).get().getUsername(),
                userRepository.findUserByUsername(userDetails.getUsername()).get().getEmail()));
    }

}
