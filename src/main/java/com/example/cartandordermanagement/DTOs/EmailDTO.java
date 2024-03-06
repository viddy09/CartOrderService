package com.example.cartandordermanagement.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    private String recipientEmail;
    private String message;

    public EmailDTO(String recipientEmail, String message){
        this.recipientEmail = recipientEmail;
        this.message = message;
    }
}
