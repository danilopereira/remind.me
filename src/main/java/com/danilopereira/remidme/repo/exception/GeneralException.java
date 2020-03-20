package com.danilopereira.remidme.repo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {
    private static final long serialVersionUID = 2375804654458857951L;

    private static final String GENERAL_ERROR_MESSAGE = "General Error";

    public GeneralException() {
        super(GENERAL_ERROR_MESSAGE);
    }
}
