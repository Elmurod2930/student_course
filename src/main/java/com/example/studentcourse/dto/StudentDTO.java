package com.example.studentcourse.dto;

import com.example.studentcourse.entity.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private StudentGender gender;
    private LocalDateTime createdDate;

}
