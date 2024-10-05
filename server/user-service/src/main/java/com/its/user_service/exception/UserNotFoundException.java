package com.its.user_service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with id " + id + " not found");
    }
}
