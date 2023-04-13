package com.example.studentcourse.model;

import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentCourseMarkModel {
    private String id;
    private StudentEntity student;
    private CourseEntity course;
}
