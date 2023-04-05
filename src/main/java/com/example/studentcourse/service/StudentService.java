package com.example.studentcourse.service;

import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        if ()
    }
}
