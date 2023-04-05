package com.example.studentcourse.repository;

import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.entity.enums.StudentGender;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    public List<StudentEntity> findAllByName(String name);

    public List<StudentEntity> findAllBySurname(String surname);

    public List<StudentEntity> findAllByLevel(Integer level);

    public List<StudentEntity> findAllByAge(Integer age);

    public List<StudentEntity> findAllByGender(String gender);

    public List<StudentEntity> findAllByCreatedDate(LocalDateTime createdDate);

    public List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
