package com.example.studentcourse.service;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.dto.StudentCourseMarkDTO;
import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentCourseMarkEntity;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.exp.CourseNotFoundException;
import com.example.studentcourse.exp.StudentCourseMarkNotFountException;
import com.example.studentcourse.exp.StudentNotFoundException;
import com.example.studentcourse.mapper.CourseInfoMapper;
import com.example.studentcourse.model.StudentCourseMarkModel;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentCourseMarkRepository;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


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
        entity.setMark(dto.getMark());
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

    // 7
    public List<Integer> getMarkByDate(LocalDate date, Integer id) {
        StudentEntity student = getByStudentEntity(id);
        LocalDateTime fromDate = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(date, LocalTime.MAX);
        List<Integer> markList = studentCourseMarkRepository.findByStudentAndMarkDateBetween(student, fromDate, toDate);
        return markList;
    }

    // 8
    public List<Integer> getByDateBetween(Integer id, LocalDate fromDate, LocalDate toDate) {
        StudentEntity student = getByStudentEntity(id);
        LocalDateTime fDate = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime tDate = LocalDateTime.of(toDate, LocalTime.MAX);
        List<Integer> list = studentCourseMarkRepository.findByStudentAndMarkDateBetween(student, fDate, tDate);
        return list;
    }

    // 9
    public List<Integer> getByStudentMarkDesc(Integer id) {
        StudentEntity student = getByStudentEntity(id);
        List<Integer> list = studentCourseMarkRepository.findAllByStudentMarkDesc(student);
        return list;
    }

    // 10
    public List<Integer> getByStudentCourseMarkDateDesc(Integer courseId, Integer studentId) {
        StudentEntity student = getByStudentEntity(studentId);
        CourseEntity course = getByCourseEntity(courseId);
        List<Integer> list = studentCourseMarkRepository.findAllByStudentCourseMarkDateDesc(student, course);
        return list;
    }

    // 11
    public StudentCourseMarkModel getLastMark(Integer studentId) {
        StudentEntity student = getByStudentEntity(studentId);
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findByLastMark(student);
        StudentCourseMarkModel model = new StudentCourseMarkModel(entity.getId(), entity.getStudent(), entity.getCourse());
        return model;
    }

    // 12
    public List<Integer> getStudent3BiggerMark(Integer studentId) {
        StudentEntity student = getByStudentEntity(studentId);
        List<Integer> list = studentCourseMarkRepository.findByStudent3BiggestMark(student);
        return list;
    }

    // 13
    public Integer getStudentFirstMark(Integer studentId) {
        StudentEntity student = getByStudentEntity(studentId);
        Integer mark = studentCourseMarkRepository.findByStudentFirstMark(student);
        return mark;
    }

    // 14
    public Integer getStudentCourseFirstMark(Integer studentId, Integer courseId) {
        StudentEntity student = getByStudentEntity(studentId);
        CourseEntity course = getByCourseEntity(courseId);
        Integer mark = studentCourseMarkRepository.findByStudentCourseFirstMark(student, course);
        return mark;
    }

    // 15
    public Integer getStudentCourseBiggerMark(Integer studentId, Integer courseId) {
        StudentEntity student = getByStudentEntity(studentId);
        CourseEntity course = getByCourseEntity(courseId);
        Integer mark = studentCourseMarkRepository.findByStudentCourseBiggestMark(student, course);
        return mark;
    }

    // 16
    public Double getStudentAvgMark(Integer studentId) {
        StudentEntity student = getByStudentEntity(studentId);
        Double avgMark = studentCourseMarkRepository.findByStudentAVGMark(student);
        return avgMark;
    }

    // 17
    public Double getStudentCourseAvgMark(Integer studentId, Integer courseId) {
        StudentEntity student = getByStudentEntity(studentId);
        CourseEntity course = getByCourseEntity(courseId);
        Double avgMark = studentCourseMarkRepository.findByStudentAndCourseAVGMark(student, course);
        return avgMark;
    }

    // 18
    public Integer getStudentCountMark(Integer studentId, Integer mark) {
        StudentEntity student = getByStudentEntity(studentId);
        Integer count = studentCourseMarkRepository.findByStudentCountMark(student, mark);
        return count;
    }

    // 19
    public Integer getCourseMaxMark(Integer courseId) {
        CourseEntity course = getByCourseEntity(courseId);
        Integer maxMark = studentCourseMarkRepository.findByMaxMark(course);
        return maxMark;
    }

    // 20
    public Double getCourseAvgMark(Integer courseId) {
        CourseEntity course = getByCourseEntity(courseId);
        Double mark = studentCourseMarkRepository.findByAvgMark(course);
        return mark;
    }

    // 21
    public Integer getCourseCountMark(Integer courseId) {
        CourseEntity course = getByCourseEntity(courseId);
        Integer count = studentCourseMarkRepository.findByCountMark(course);
        return count;
    }

    // =========================================================================================================
    public StudentEntity getByStudentEntity(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("not fount student");
        }
        return optional.get();
    }

    public CourseEntity getByCourseEntity(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CourseNotFoundException("not fount course");
        }
        return optional.get();
    }

    public StudentCourseMarkDTO getStudentCourseMarkDTObyEntity(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public void test() {
        List<Object[]> courseObjList = studentCourseMarkRepository.findLastCourseMarkerAsNative(1);
        if (courseObjList.size() > 0) {
            Object[] courseObj = courseObjList.get(0);

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId((Integer) courseObj[0]);
            courseDTO.setName((String) courseObj[1]);
            System.out.println(courseDTO);
        }

        System.out.println("dasda");
    }

    public void test2() {
        CourseInfoMapper courseInfoMapper = studentCourseMarkRepository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO + " " + courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }

    public Page<StudentCourseMarkDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(entity.getMark());
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }

        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentCourseMarkDTO> pagingWithStudentId(Integer studentId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByStudentId(studentId, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(entity.getMark());
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentCourseMarkDTO> pagingWithCourseId(Integer studentId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByCourseId(studentId, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(entity.getMark());
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }
}
