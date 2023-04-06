package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentCourseMarkDTO;
import com.example.studentcourse.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/StudentCourseMark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkDTO dto = studentCourseMarkService.create(studentCourseMarkDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, studentCourseMarkDTO));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        StudentCourseMarkDTO dto = studentCourseMarkService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll() {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getMarkByDate/{date}")
    public ResponseEntity<?> getMarkByDate(@PathVariable LocalDateTime date) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getMarkByDate(date);
        return ResponseEntity.ok(list);
    }

}
