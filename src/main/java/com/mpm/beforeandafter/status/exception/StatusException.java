package com.mpm.beforeandafter.status.exception;

public class StatusException extends RuntimeException{
    public StatusException(String test) {
        super("Fill in the parameter and provide the text here." + test);
    }
}