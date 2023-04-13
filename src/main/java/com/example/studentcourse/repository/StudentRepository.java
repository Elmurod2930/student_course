package com.example.studentcourse.repository;

import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.mapper.StudentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {
    public List<StudentEntity> findAllByName(String name);

    public List<StudentEntity> findAllBySurname(String surname);

    public List<StudentEntity> findAllByLevel(Integer level);

    public List<StudentEntity> findAllByAge(Integer age);

    public List<StudentEntity> findAllByGender(String gender);

    public List<StudentEntity> findAllByCreatedDate(LocalDateTime createdDate);

    public List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    // ================================================
    @Query("from StudentEntity where name=:name")
    List<StudentEntity> getByName(@Param("name") String name);

    @Query("from StudentEntity where surname=:surname")
    List<StudentEntity> getBySurname(@Param("surname") String surname);


//    @Transactional
//    @Modifying
//    @Query("update student set visible =:visible where id=:sid")
//    Integer changeVisibility(@Param("sId") Integer sid, @Param("visible") Boolean v);

    @Query("from StudentEntity where name like ?1")
    List<StudentEntity> findByName2(String name);

    @Query("select new StudentEntity(id,name,surname) from StudentEntity ")
    List<StudentEntity> findByName4();

    @Query("SELECT new com.example.studentcourse.mapper.StudentMapper(id,name, phone) FROM StudentEntity ")
    List<StudentMapper> findByName5();

    List<StudentEntity> findAllByPhoneIn(List<String> phoneList);

    // =================================================================================================================
    Page<StudentEntity> findAllByName(String name, Pageable pageable);
    Page<StudentEntity> findAllByLevel(Integer level, Pageable pageable);
    Page<StudentEntity> findAllByGender(String gender, Pageable pageable);
}
