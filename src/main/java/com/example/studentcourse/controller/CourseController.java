package com.example.studentcourse.controller;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDTO dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.create(courseDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.update(id, courseDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        List<CourseDTO> courseList = courseService.getByName(name);
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/getByPrice/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable Integer price) {
        List<CourseDTO> courseDTOList = courseService.getByPrice(price);
        return ResponseEntity.ok(courseDTOList);
    }

    @GetMapping("/getByDurasion/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable Integer duration) {
        List<CourseDTO> courseDTOList = courseService.getByDuration(duration);
        return ResponseEntity.ok(courseDTOList);
    }

//    @GetMapping("/getByDateBetween/{fromDate,toDate}")
//    public ResponseEntity<?> getByDateBetween(@PathVariable LocalDateTime fromDate, @PathVariable LocalDateTime toDate) {
//        List<CourseDTO> courseDTOList = courseService.getByDateBetween(fromDate, toDate);
//        return ResponseEntity.ok(courseDTOList);
//    }

//    @GetMapping("/getByPriceBetween/{fromPrice,toPrice}")
//    public ResponseEntity<?> getByDuration(@PathVariable Integer fromPrice, @PathVariable Integer toPrice) {
//        List<CourseDTO> courseDTOList = courseService.getByPriceBetween(fromPrice, toPrice);
//        return ResponseEntity.ok(courseDTOList);
//    }
}
