package com.example.studentcourse.controller;

import com.example.studentcourse.exp.AppBadRequestException;
import com.example.studentcourse.exp.CourseNotFoundException;
import com.example.studentcourse.exp.StudentCourseMarkNotFountException;
import com.example.studentcourse.exp.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({AppBadRequestException.class, CourseNotFoundException.class,
            StudentNotFoundException.class, StudentCourseMarkNotFountException.class})

    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

//    @ExceptionHandler(PhoneAlreadyExistsException.class)
//    public ResponseEntity<String> handleException(PhoneAlreadyExistsException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
