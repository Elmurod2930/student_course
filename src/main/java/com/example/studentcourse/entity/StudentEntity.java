package com.example.studentcourse.entity;

import com.example.studentcourse.entity.enums.StudentGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "level")
    private Integer level;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private StudentGender gender;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
