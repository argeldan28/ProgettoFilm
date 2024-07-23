package com.generation.progettofilm.progetto_film.auth.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String role;
    private String tokenType = "Bearer ";

    public AuthResponseDto(String accessToken,String role) 
    {
        this.accessToken = accessToken;
        this.role = role;
    }
}
