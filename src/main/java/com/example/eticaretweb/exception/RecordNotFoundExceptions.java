package com.example.eticaretweb.exception;

public class RecordNotFoundExceptions extends RuntimeException{
    public int code;
    public String message;

    public RecordNotFoundExceptions(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
