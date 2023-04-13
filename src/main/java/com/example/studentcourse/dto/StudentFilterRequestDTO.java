package com.example.studentcourse.dto;

import com.example.studentcourse.entity.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilterRequestDTO {
    private String name;
    private Integer level;
    private StudentGender gender;
}

