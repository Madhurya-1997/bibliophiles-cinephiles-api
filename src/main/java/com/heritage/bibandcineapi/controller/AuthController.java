package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.AuthRequest;
import com.heritage.bibandcineapi.models.AuthResponse;
import com.heritage.bibandcineapi.repository.UserRepository;
import com.heritage.bibandcineapi.security.JwtUtil;
import com.heritage.bibandcineapi.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


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
