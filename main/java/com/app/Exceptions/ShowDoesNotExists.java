package com.app.Exceptions;

public class ShowDoesNotExists extends RuntimeException{

    public ShowDoesNotExists() {
        super("Show does not exists");
    }
}
