package com.example.studentcourse.repository;

import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentCourseMarkEntity;
import com.example.studentcourse.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, String> {
    // 7
    public List<StudentCourseMarkEntity> findAllByCreatedDate(LocalDateTime date);

    // 8
    public List<StudentCourseMarkEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    // 9
    List<StudentCourseMarkEntity> findAllByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);

    // 10
    List<StudentCourseMarkEntity> findAllByStudentAndCourseOrderByCreatedDateDesc(StudentEntity student, CourseEntity course);

    // ========================================================================

    // 7
    @Query("select mark from StudentCourseMarkEntity where student =:student and createdDate=:createdDate")
    List<Integer> findAllMarkByDate(@Param("student") StudentEntity student, @Param("createdDate") LocalDateTime date);

    // 8
    @Query("select mark from StudentCourseMarkEntity where createdDate > :fromDate and createdDate < :toDate")
    List<Integer> findByStudentAndMarkDateBetween(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

    // 9
    @Query("select mark from StudentCourseMarkEntity where student=:student order by createdDate desc ")
    List<Integer> findAllByStudentMarkDesc(@Param("student") StudentEntity student);

    // 10
    @Query("select mark from StudentCourseMarkEntity where student=:student and course=:course order by createdDate desc ")
    List<Integer> findAllByStudentMarkDateDesc(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 11
    @Query("select new StudentCourseMarkEntity(id,student,course) from StudentCourseMarkEntity where student=:student order by createdDate desc limit 1")
    StudentCourseMarkEntity findByStudentLastMark(@Param("student") StudentEntity student);

    // 12
    @Query("select mark from StudentCourseMarkEntity where student=:student order by mark desc limit 3")
    List<Integer> findByStudent3BiggestMark(@Param("student") StudentEntity student);

    // 13
    @Query("select mark from StudentCourseMarkEntity where student=:student order by createdDate limit 1")
    List<Integer> findByStudentFirstMark(@Param("student") StudentEntity student);

    // 14
    @Query("select mark from StudentCourseMarkEntity where student=:student and course=:course order by createdDate limit 1")
    List<Integer> findByStudentFirstMark(@Param("student") StudentEntity student,@Param("course") CourseEntity course);

    // 15
    @Query("select max(mark) from StudentCourseMarkEntity where student=:student and course=:course")
    Integer findByStudentBiggestMark(@Param("student") StudentEntity student,@Param("course") CourseEntity course);

    // 16
    @Query("select AVG(mark) from StudentCourseMarkEntity where student=:student")
    Integer findByStudentAVGMark(@Param("student") StudentEntity student);

    // 17
    @Query("select AVG(mark) from StudentCourseMarkEntity where student=:student and course=:course")
    Integer findByStudentAndCourseAVGMark(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 19
    @Query("select max(mark) from StudentCourseMarkEntity where course=:course")
    Integer findByMaxMark(@Param("course") CourseEntity course);

    // 20
    @Query("select AVG(mark) from StudentCourseMarkEntity where course=:course")
    Integer findByAvgMark(@Param("course") CourseEntity course);

    // 21
    @Query("select count(*) from StudentCourseMarkEntity where course=:course")
    Integer findByCountMark(@Param("course") CourseEntity course);

}
