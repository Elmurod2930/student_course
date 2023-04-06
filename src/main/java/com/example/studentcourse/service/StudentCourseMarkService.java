package com.example.studentcourse.service;

import com.example.studentcourse.dto.StudentCourseMarkDTO;
import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentCourseMarkEntity;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.exp.CourseNotFoundException;
import com.example.studentcourse.exp.StudentCourseMarkNotFountException;
import com.example.studentcourse.exp.StudentNotFoundException;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentCourseMarkRepository;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto) {
        CourseEntity course = courseRepository.findById(dto.getCourse_id()).get();
        if (course == null) {
            throw new CourseNotFoundException("course not fount");
        }
        StudentEntity student = studentRepository.findById(dto.getStudent_id()).get();
        if (student == null) {
            throw new StudentNotFoundException("student not fount");
        }
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCourse(course);
        entity.setStudent(student);
        studentCourseMarkRepository.save(entity);
        return dto;
    }

    public Boolean update(String id, StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();
        if (entity == null) {
            throw new StudentCourseMarkNotFountException("StudentCourseMark not fount");
        }
        CourseEntity course = courseRepository.findById(dto.getCourse_id()).get();
        if (course == null) {
            throw new CourseNotFoundException("course not fount");
        }
        StudentEntity student = studentRepository.findById(dto.getStudent_id()).get();
        if (student == null) {
            throw new StudentNotFoundException("student not fount");
        }
        entity.setId(id);
        entity.setStudent(student);
        entity.setCourse(course);
        studentCourseMarkRepository.save(entity);
        return true;
    }

    public StudentCourseMarkDTO getById(String id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();
        if (entity == null) {
            throw new StudentCourseMarkNotFountException("StudentCourseMark not fount");
        }
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean delete(String id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();
        if (entity == null) {
            throw new StudentCourseMarkNotFountException("StudentCourseMark not fount");
        }
        studentCourseMarkRepository.delete(entity);
        return true;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        iterable.forEach(entity -> {
            list.add(getStudentCourseMarkDTObyEntity(entity));
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getMarkByDate(LocalDateTime date) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findAllByCreatedDate(date);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            list.add(getStudentCourseMarkDTObyEntity(studentCourseMarkEntity));
        });
        return list;
    }

    public StudentCourseMarkDTO getStudentCourseMarkDTObyEntity(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
