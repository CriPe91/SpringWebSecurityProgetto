package com.example.SpringWebSecurityProgetto.exception;

public class UsernameDuplicatoException extends RuntimeException {
    public UsernameDuplicatoException(String message) {
        super(message);
    }
}
