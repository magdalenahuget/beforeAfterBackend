package com.mpm.beforeandafter.role.exception;
public class RoleException extends RuntimeException{
    public RoleException(String test){
        super("Fill in the parameter and provide the text here." + test);
    }
}