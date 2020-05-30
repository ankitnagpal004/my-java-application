package com.abc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TextSearchValidationException extends RuntimeException {
	private static final long serialVersionUID = -272637691190824419L;

	public TextSearchValidationException(String message) {
        super(message);
    }
}