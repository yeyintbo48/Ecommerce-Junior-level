package com.project.e_commerce.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;
}
