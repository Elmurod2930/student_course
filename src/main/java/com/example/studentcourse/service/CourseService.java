package com.example.studentcourse.service;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.exp.AppBadRequestException;
import com.example.studentcourse.exp.CourseNotFoundException;
import com.example.studentcourse.repository.CourseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> courseList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setName(entity.getName());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            courseList.add(dto);
        });
        return courseList;
    }

    public CourseDTO getById(Integer id) {
        if (id == null) {
            throw new AppBadRequestException("id qani?");
        }
        Optional<CourseEntity> optional = courseRepository.findById(id);
        CourseEntity entity = optional.get();
        CourseDTO dto = new CourseDTO();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        return dto;
    }

    public CourseDTO create(CourseDTO courseDTO) {
        if (courseDTO.getName() == null || courseDTO.getName().isBlank()) {
            throw new AppBadRequestException("name qani?");
        }
        if (courseDTO.getPrice() == null) {
            throw new AppBadRequestException("price qani?");
        }
        CourseEntity entity = new CourseEntity();
        entity.setPrice(courseDTO.getPrice());
        entity.setName(courseDTO.getName());
        courseRepository.save(entity);
        return courseDTO;
    }

    public Boolean update(Integer id, CourseDTO courseDTO) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CourseNotFoundException("bunaqa course mavjud emas");
        }
        CourseEntity entity = optional.get();
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setId(id);
        courseRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CourseNotFoundException("bunaqa course mavjud emas");
        }
        courseRepository.delete(optional.get());
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        if (name == null || name.isBlank()) {
            throw new AppBadRequestException("name qani?");
        }
        List<CourseEntity> list = courseRepository.findAllByName(name);
        return getDtoListByEntityList(list);
    }

    public List<CourseDTO> getByPrice(Integer price) {
        if (price == null) {
            throw new AppBadRequestException("price qani?");
        }
        List<CourseEntity> list = courseRepository.findAllByPrice(price);
        return getDtoListByEntityList(list);
    }

    public List<CourseDTO> getDtoListByEntityList(List<CourseEntity> entityList) {
        List<CourseDTO> courseList = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            courseList.add(dto);
        });
        return courseList;
    }

    public List<CourseDTO> getByDuration(Integer duration) {
        if (duration == null) {
            throw new AppBadRequestException("duration qani?");
        }
        List<CourseEntity> list = courseRepository.findAllByDuration(duration);
        return getDtoListByEntityList(list);
    }

    public List<CourseDTO> getByDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate == null || toDate == null) {
            throw new AppBadRequestException("date qani?");
        }
        List<CourseEntity> list = courseRepository.findAllByCreatedDateBetween(fromDate, toDate);
        return getDtoListByEntityList(list);
    }

    public List<CourseDTO> getByPriceBetween(Integer fromPrice, Integer toPrice) {
        if (fromPrice == null || toPrice == null) {
            throw new AppBadRequestException("date qani?");
        }
        List<CourseEntity> list = courseRepository.findAllByPriceBetween(fromPrice, toPrice);
        return getDtoListByEntityList(list);
    }

    public Page<CourseDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CourseEntity> pageObj = courseRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();
        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDuration(entity.getDuration());
            dto.setPrice(entity.getPrice());
            dtoList.add(dto);
        }

        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<CourseDTO> paginationWithPrice(Integer price, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CourseEntity> pageObj = courseRepository.findAllByPrice(price, paging);
        Long totalCount = pageObj.getTotalElements();
        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDuration(entity.getDuration());
            dto.setPrice(entity.getPrice());
            dtoList.add(dto);
        }

        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<CourseDTO> paginationWithPriceBetween(Integer fromPrice, Integer toPrice, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CourseEntity> pageObj = courseRepository.findAllByPriceBetween(fromPrice,toPrice, paging);
        Long totalCount = pageObj.getTotalElements();
        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDuration(entity.getDuration());
            dto.setPrice(entity.getPrice());
            dtoList.add(dto);
        }

        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, paging, totalCount);
        return response;
    }
}
