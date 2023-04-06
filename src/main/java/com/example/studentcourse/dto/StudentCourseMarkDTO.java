package com.example.studentcourse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseMarkDTO {
    private String id;
    private Integer student_id;
    private Integer course_id;
    private Integer mark;
    private LocalDateTime createdDate;
}
