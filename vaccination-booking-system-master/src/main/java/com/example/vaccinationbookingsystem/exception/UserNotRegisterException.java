package com.example.vaccinationbookingsystem.exception;

public class UserNotRegisterException extends RuntimeException {

    public UserNotRegisterException(String message){
        super(message);
    }
}
