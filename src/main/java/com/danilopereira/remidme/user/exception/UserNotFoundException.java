package com.danilopereira.remidme.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    private final static String MESSAGE = "User not found!";

    private static final long serialVersionUID = 1931366261327089581L;

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
