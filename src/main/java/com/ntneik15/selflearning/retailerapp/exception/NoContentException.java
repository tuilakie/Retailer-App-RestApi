package com.ntneik15.selflearning.retailerapp.exception;

public class NoContentException extends RuntimeException{
    public NoContentException() {
        super();
    }
    public NoContentException(String message) {
        super(message);
    }
    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
