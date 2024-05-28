package com.company.reverselog.controller.exception;


public class ControllerNotFoundExeption extends  RuntimeException{
    public ControllerNotFoundExeption(String message) {
        super(message);
    }
}
