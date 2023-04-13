package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentCourseMarkDTO;
import com.example.studentcourse.dto.StudentCourseMarkFilterDTO;
import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.model.StudentCourseMarkModel;
import com.example.studentcourse.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/studentCourseMark")
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

    // 7
    @GetMapping("/getMarkByDate")
    public ResponseEntity<?> getMarkByDate(@RequestParam LocalDate date, @RequestParam Integer id) {
        List<Integer> list = studentCourseMarkService.getMarkByDate(date, id);
        return ResponseEntity.ok(list);
    }

    // 8
    @GetMapping("/getByDateBetween")
    public ResponseEntity<?> getByDateBetween(@RequestParam Integer id, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        List<Integer> list = studentCourseMarkService.getByDateBetween(id, fromDate, toDate);
        return ResponseEntity.ok(list);
    }

    // 9
    @GetMapping("/getByStudentMarkDesc/{id}")
    public ResponseEntity<?> getByStudentMarkDesc(@PathVariable Integer id) {
        List<Integer> list = studentCourseMarkService.getByStudentMarkDesc(id);
        return ResponseEntity.ok(list);
    }

    // 10
    @GetMapping("/getByStudentCourseMarkDateDesc")
    public ResponseEntity<?> getByStudentCourseMarkDateDesc(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        List<Integer> list = studentCourseMarkService.getByStudentCourseMarkDateDesc(courseId, studentId);
        return ResponseEntity.ok(list);
    }

    // 11
    @GetMapping("/getStudentLastMark/{id}")
    public ResponseEntity<?> getStudentLastMark(@PathVariable Integer id) {
        StudentCourseMarkModel dto = studentCourseMarkService.getLastMark(id);
        return ResponseEntity.ok(dto);
    }

    // 12
    @GetMapping("/getStudent3BiggerMark/{id}")
    public ResponseEntity<?> getStudent3BiggerMark(@PathVariable Integer id) {
        List<Integer> list = studentCourseMarkService.getStudent3BiggerMark(id);
        return ResponseEntity.ok(list);
    }

    // 13
    @GetMapping("/getStudentFirstMark/{id}")
    public ResponseEntity<?> getStudentFirstMark(@PathVariable Integer id) {
        Integer mark = studentCourseMarkService.getStudentFirstMark(id);
        return ResponseEntity.ok(mark);
    }

    // 14
    @GetMapping("/getStudentCourseFirstMark")
    public ResponseEntity<?> getStudentCourseFirstMark(@RequestParam Integer sId, @RequestParam Integer cId) {
        Integer mark = studentCourseMarkService.getStudentCourseFirstMark(sId, cId);
        return ResponseEntity.ok(mark);
    }

    // 15
    @GetMapping("/getStudentCourseBiggestMark")
    public ResponseEntity<?> getStudentCourseBiggestMark(@RequestParam Integer sId, @RequestParam Integer cId) {
        Integer mark = studentCourseMarkService.getStudentCourseBiggerMark(sId, cId);
        return ResponseEntity.ok(mark);
    }

    // 16
    @GetMapping("/getStudentAvgMark/{id}")
    public ResponseEntity<?> getStudentAvgMark(@PathVariable Integer id) {
        Double avg = studentCourseMarkService.getStudentAvgMark(id);
        return ResponseEntity.ok(avg);
    }

    // 17
    @GetMapping("/getStudentCourseAvgMark")
    public ResponseEntity<?> getStudentCourseAvgMark(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        Double avgMark = studentCourseMarkService.getStudentCourseAvgMark(studentId, courseId);
        return ResponseEntity.ok(avgMark);
    }

    // 18
    @GetMapping("/getStudentCountMark")
    public ResponseEntity<?> getStudentCountMark(@RequestParam Integer studentId, @RequestParam Integer mark) {
        Integer count = studentCourseMarkService.getStudentCountMark(studentId, mark);
        return ResponseEntity.ok(count);
    }

    // 19
    @GetMapping("/getCourseMaxMark/{id}")
    public ResponseEntity<?> getCourseMaxMark(@PathVariable Integer id) {
        Integer mark = studentCourseMarkService.getCourseMaxMark(id);
        return ResponseEntity.ok(mark);
    }

    // 20
    @GetMapping("/getCourseAvgMark/{id}")
    public ResponseEntity<?> getCourseAvgMark(@PathVariable Integer id) {
        Double mark = studentCourseMarkService.getCourseAvgMark(id);
        return ResponseEntity.ok(mark);
    }

    // 21
    @GetMapping("/getCourseCountMark/{id}")
    public ResponseEntity<?> getCourseCountMark(@PathVariable Integer id) {
        Integer count = studentCourseMarkService.getCourseCountMark(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/paging")
    public ResponseEntity<?> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "30") int size) {
        Page<StudentCourseMarkDTO> response = studentCourseMarkService.pagination(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/paging-studentId")
    public ResponseEntity<?> pagingWithStudentId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "30") int size,
                                                 @RequestBody StudentCourseMarkFilterDTO filter) {
        Page<StudentCourseMarkDTO> response = studentCourseMarkService.pagingWithStudentId(filter.getStudentId(), page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/paging-courseId")
    public ResponseEntity<?> pagingWithCourseId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "size", defaultValue = "30") int size,
                                                @RequestBody StudentCourseMarkFilterDTO filter){
        Page<StudentCourseMarkDTO> response = studentCourseMarkService.pagingWithCourseId(filter.getStudentId(), page, size);
        return ResponseEntity.ok(response);
    }
}
