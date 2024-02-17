package com.app.Exceptions;

public class TheaterDoesNotExists extends RuntimeException{
    public TheaterDoesNotExists() {
        super("Theater does not Exists");
    }
}
