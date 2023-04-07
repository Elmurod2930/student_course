package com.example.studentcourse.service;

import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.entity.enums.StudentGender;
import com.example.studentcourse.exp.AppBadRequestException;
import com.example.studentcourse.exp.StudentNotFoundException;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("name qani?");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("surname qani?");
        }
        if (dto.getAge() == null) {
            throw new AppBadRequestException("age qani?");
        }
        if (dto.getGender() == null) {
            throw new AppBadRequestException("gender qani?");
        }
        if (dto.getLevel() == null) {
            throw new AppBadRequestException("lever qani?");
        }
        StudentEntity entity = getEntityByDto(dto);
        studentRepository.save(entity);
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = getDtoByEntity(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        StudentEntity entity = optional.get();
        return getDtoByEntity(entity);
    }

    public Boolean update(Integer id, StudentDTO studentDTO) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("bunaqa student topilmadi");
        }
        StudentEntity entity = optional.get();
        entity.setId(id);
        if (studentDTO.getName() != null) {
            entity.setName(studentDTO.getName());
        }
        if (studentDTO.getSurname() != null) {
            entity.setSurname(studentDTO.getSurname());
        }
        if (studentDTO.getAge() != null) {
            entity.setAge(studentDTO.getAge());
        }
        if (studentDTO.getGender() != null) {
            entity.setGender(studentDTO.getGender());
        }
        if (studentDTO.getLevel() != null) {
            entity.setLevel(studentDTO.getLevel());
        }
        studentRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("bunaqa student topilmadi");
        }
        studentRepository.delete(optional.get());
        return true;
    }

    public List<StudentDTO> getByName(String name) {
        if (name == null || name.isBlank()) {
            throw new AppBadRequestException("name qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByName(name);
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getBySurname(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new AppBadRequestException("surname qani?");
        }
        List<StudentEntity> list = studentRepository.findAllBySurname(surname);
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getByLevel(Integer level) {
        if (level == null) {
            throw new AppBadRequestException("level qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByLevel(level);
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getByAge(Integer age) {
        if (age == null) {
            throw new AppBadRequestException("agr qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByAge(age);
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getByGender(StudentGender gender) {
        if (gender == null) {
            throw new AppBadRequestException("gender qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByGender(gender.name());
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getByCreatedDate(LocalDateTime date) {
        if (date == null) {
            throw new AppBadRequestException("date qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByCreatedDate(date);
        return getDtoListByEntityList(list);
    }

    public List<StudentDTO> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate == null || toDate == null) {
            throw new AppBadRequestException("date qani?");
        }
        List<StudentEntity> list = studentRepository.findAllByCreatedDateBetween(fromDate, toDate);
        return getDtoListByEntityList(list);
    }

    public StudentEntity getEntityByDto(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        return entity;
    }

    public StudentDTO getDtoByEntity(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setGender(entity.getGender());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentDTO> getDtoListByEntityList(List<StudentEntity> list) {
        List<StudentDTO> studentList = new LinkedList<>();
        list.forEach(entity -> {
            StudentDTO dto = getDtoByEntity(entity);
            studentList.add(dto);
        });
        return studentList;
    }


}
