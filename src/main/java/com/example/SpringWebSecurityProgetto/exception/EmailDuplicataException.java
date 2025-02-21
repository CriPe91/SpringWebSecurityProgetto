package com.example.SpringWebSecurityProgetto.exception;

public class EmailDuplicataException extends RuntimeException {
    public EmailDuplicataException(String message) {
        super(message);
    }
}
