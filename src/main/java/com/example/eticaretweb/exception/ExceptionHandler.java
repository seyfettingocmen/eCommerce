package com.example.eticaretweb.exception;

import com.example.eticaretweb.response.BaseResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(RecordNotFoundExceptions.class)
    public ResponseEntity<Object> handleException(RecordNotFoundExceptions e) {
        System.out.println(e.getMessage());
        BaseResponse response = new BaseResponse();
        response.code = e.code;
        response.message = e.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleException(NullPointerException e) {
        System.out.println(e.getMessage());
        BaseResponse response = new BaseResponse();
        response.code = 4000;
        response.message = "Null Pointer Exception";
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
}
