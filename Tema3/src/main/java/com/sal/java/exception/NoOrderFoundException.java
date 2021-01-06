package com.sal.java.exception;

public class NoOrderFoundException extends RuntimeException{
    public NoOrderFoundException() {
        super("No order found!");
    }
}
