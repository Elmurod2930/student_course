package com.example.studentcourse.repository;

import com.example.studentcourse.entity.StudentCourseMarkEntity;
import com.example.studentcourse.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, String> {
    public List<StudentCourseMarkEntity> findAllByCreatedDate(LocalDateTime date);

    public List<StudentCourseMarkEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    public List<StudentCourseMarkEntity> findAllByOrderByMarkDesc(StudentEntity student);
    public List<StudentCourseMarkEntity> findAllByMarkOrderByCreatedDate(StudentEntity student);

}
