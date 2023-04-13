package com.example.studentcourse.repository;

import com.example.studentcourse.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>,
        PagingAndSortingRepository<CourseEntity, Integer> {
    public List<CourseEntity> findAllByName(String name);

    public List<CourseEntity> findAllByDuration(Integer duration);

    public List<CourseEntity> findAllByPrice(Integer price);

    public List<CourseEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    public List<CourseEntity> findAllByPriceBetween(Integer fromPrice, Integer toPrice);

    Page<CourseEntity> findAllByPrice(Integer price, Pageable paging);

    Page<CourseEntity> findAllByPriceBetween(Integer fromPrice, Integer toPrice, Pageable paging);
}
