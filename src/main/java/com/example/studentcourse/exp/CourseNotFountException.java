package com.example.studentcourse.exp;

public class CourseNotFountException extends RuntimeException{
    public CourseNotFountException(String message) {
        super(message);
    }
}
