package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.AuthRequest;
import com.heritage.bibandcineapi.models.AuthResponse;
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
public class HomeController {


    @GetMapping("/")
    public String welcome() {
        return ("<h1>Welcome to Bibliophiles and Cinephiles API !!</h1>");
    }


    @GetMapping("/api")
    public String api() {
        return ("<h1>You have access to our APIs !!</h1>");
    }

}
