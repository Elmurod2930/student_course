package com.example.studentcourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Student_course_mark")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column(name = "mark")
    private Integer mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public StudentCourseMarkEntity(String id, StudentEntity student, CourseEntity course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }
}
