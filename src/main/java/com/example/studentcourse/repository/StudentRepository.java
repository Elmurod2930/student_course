package com.example.studentcourse.repository;

import com.example.studentcourse.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
}
