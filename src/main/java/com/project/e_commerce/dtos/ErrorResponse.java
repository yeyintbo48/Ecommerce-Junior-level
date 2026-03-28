package com.project.e_commerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status; //400
    private String error; //Bad Request
    private String message; //Not Enough
}
