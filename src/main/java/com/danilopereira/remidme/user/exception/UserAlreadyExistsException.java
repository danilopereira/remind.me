package com.danilopereira.remidme.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -3867139523327732582L;

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User already exists!";

    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS_MESSAGE);
    }
}
