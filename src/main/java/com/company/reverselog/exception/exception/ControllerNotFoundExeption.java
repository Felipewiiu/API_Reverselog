package com.company.reverselog.exception.exception;


public class ControllerNotFoundExeption extends  RuntimeException{
    public ControllerNotFoundExeption(String message) {
        super(message);
    }
}
