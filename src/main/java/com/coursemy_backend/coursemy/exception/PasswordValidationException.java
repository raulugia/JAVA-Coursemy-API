package com.coursemy_backend.coursemy.exception;

public class PasswordValidationException extends RuntimeException {
    public PasswordValidationException() {
        super("Password must be 6-20 characters long, contain at least one uppercase letter, and at least one lowercase letter.");
    }
}
