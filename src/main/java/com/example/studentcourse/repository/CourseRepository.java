package com.example.studentcourse.repository;

import com.example.studentcourse.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    public List<CourseEntity> findAllByName(String name);

    public List<CourseEntity> findAllByDuration(Integer duration);

    public List<CourseEntity> findAllByPrice(Integer price);

    public List<CourseEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    public List<CourseEntity> findAllByPriceBetween(Integer fromPrice, Integer toPrice);
}
