package com.example.studentcourse.exp;

public class AppBadRequestException extends RuntimeException{
    public AppBadRequestException(String message) {
        super(message);
    }
}
