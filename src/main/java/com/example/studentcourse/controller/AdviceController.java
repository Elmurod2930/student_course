package com.example.studentcourse.controller;

import com.example.studentcourse.exp.AppBadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

//    @ExceptionHandler(PhoneAlreadyExistsException.class)
//    public ResponseEntity<String> handleException(PhoneAlreadyExistsException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
