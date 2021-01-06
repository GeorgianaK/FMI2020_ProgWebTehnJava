package com.sal.java.exception;

public class NoStockAvailableException extends RuntimeException{
    public  NoStockAvailableException() {
        super("No stock available!");
    }
}
