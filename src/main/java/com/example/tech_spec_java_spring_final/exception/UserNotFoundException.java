package com.example.tech_spec_java_spring_final.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("Пользователь с ID " + userId + " не найден");
    }
}