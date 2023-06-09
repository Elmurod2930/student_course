package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.dto.StudentFilterRequestDTO;
import com.example.studentcourse.entity.enums.StudentGender;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentDTO dto = studentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<StudentDTO>> getByName(@PathVariable String name) {
        List<StudentDTO> list = studentService.getByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getBySurname/{surname}")
    public ResponseEntity<List<StudentDTO>> getBySurname(@PathVariable String surname) {
        List<StudentDTO> list = studentService.getBySurname(surname);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByLevel/{level}")
    public ResponseEntity<List<StudentDTO>> getByLevel(@PathVariable Integer level) {
        List<StudentDTO> list = studentService.getByLevel(level);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<StudentDTO>> getByAge(@PathVariable Integer age) {
        List<StudentDTO> list = studentService.getByAge(age);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByGender/{gender}")
    public ResponseEntity<List<StudentDTO>> getByGender(@PathVariable StudentGender gender) {
        List<StudentDTO> list = studentService.getByGender(gender);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByGivenDate/{date}")
    public ResponseEntity<List<StudentDTO>> getByGender(@PathVariable LocalDateTime date) {
        List<StudentDTO> list = studentService.getByCreatedDate(date);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/paging")
    public ResponseEntity<Page<StudentDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "30") int size) {
        Page<StudentDTO> response = studentService.pagination(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/paging-name")
    public ResponseEntity<Page<StudentDTO>> pagingWithName(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "30") int size,
                                                           @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithName(filter.getName(), page, size);
        return ResponseEntity.ok(response);
    }

    // 10
    @PostMapping(value = "/paging-level")
    public ResponseEntity<Page<StudentDTO>> pagingWithLevel(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "30") int size,
                                                            @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithLevel(filter.getLevel(), page, size);
        return ResponseEntity.ok(response);
    }

    // 11
    @PostMapping(value = "/paging-gender")
    public ResponseEntity<Page<StudentDTO>> pagingWithGender(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "30") int size,
                                                            @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithGender(filter.getGender(), page, size);
        return ResponseEntity.ok(response);
    }


}
