package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.AuthRequest;
import com.heritage.bibandcineapi.models.AuthResponse;
import com.heritage.bibandcineapi.security.JwtUtil;
import com.heritage.bibandcineapi.services.MyUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@Slf4j
public class HomeController {

    @Operation(summary = "Display home page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully displayed home page",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/")
    public String welcome() {
        return ("<h1>Welcome to Bibliophiles and Cinephiles API !!!!!!!!!!!!!!!!!</h1>");
    }


    @Operation(summary = "Dummy API to test authorized user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Authorization Successful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Authorization Unsuccessful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/api")
    public String api(Principal principal) {
        System.out.println(principal);
        return ("<h1>You have access to our APIs !!</h1>");
    }

}
