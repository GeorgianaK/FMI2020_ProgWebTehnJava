package com.sal.java.exception;

public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException() {
        super("No product was found!");
    }
}
