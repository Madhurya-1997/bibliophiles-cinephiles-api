package com.heritage.bibandcineapi.models;

public class AuthResponse {

    private String jwt;

    public AuthResponse(){
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
