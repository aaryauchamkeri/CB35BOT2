package com.aarya.controllers;

import javax.servlet.http.HttpServletRequest;

import com.aarya.model.ServerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpExceptionHandler{
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "WRONG BODY TYPE INPUT, EXPECTING NUMBER")
    @ExceptionHandler(NumberFormatException.class)
    public void handleWrongBody(HttpServletRequest req, Exception ex){
        
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "SERVER NOT PRESENT")
    @ExceptionHandler(ServerException.class)
    public void handleNoServer(){

    }   

}