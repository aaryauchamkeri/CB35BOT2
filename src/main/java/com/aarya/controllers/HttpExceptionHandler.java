package com.aarya.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aarya.model.ServerException;

import com.aarya.model.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class HttpExceptionHandler{
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public void handleWrongBody(HttpServletRequest req, Exception ex){

    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "SERVER NOT PRESENT")
    @ExceptionHandler(ServerException.class)
    public void handleNoServer(){

    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "SERVER NOT PRESENT")
    @ExceptionHandler(UnauthorizedException.class)
    public void handleNotLoggedIn(HttpServletRequest req, HttpServletResponse res, Exception e) throws IOException {
        res.sendError(401, "not authorized");
    }

}