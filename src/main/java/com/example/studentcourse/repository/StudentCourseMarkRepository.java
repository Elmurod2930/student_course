package com.example.studentcourse.repository;

import com.example.studentcourse.entity.CourseEntity;
import com.example.studentcourse.entity.StudentCourseMarkEntity;
import com.example.studentcourse.entity.StudentEntity;
import com.example.studentcourse.mapper.CourseInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, String>,
        PagingAndSortingRepository<StudentCourseMarkEntity, String> {


    // 7,8
    public List<StudentCourseMarkEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    // 9
    List<StudentCourseMarkEntity> findAllByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);

    // 10
    List<StudentCourseMarkEntity> findAllByStudentAndCourseOrderByCreatedDateDesc(StudentEntity student, CourseEntity course);

    // =================================================================================================================

    // 7,8
    @Query("select mark from StudentCourseMarkEntity where student=:student and createdDate > :fromDate and createdDate < :toDate")
    List<Integer> findByStudentAndMarkDateBetween(@Param("student") StudentEntity student, @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

    // 9
    @Query("select mark from StudentCourseMarkEntity where student=:student order by createdDate desc ")
    List<Integer> findAllByStudentMarkDesc(@Param("student") StudentEntity student);

    // 10
    @Query("select mark from StudentCourseMarkEntity where student=:student and course=:course order by createdDate desc ")
    List<Integer> findAllByStudentCourseMarkDateDesc(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 11
    @Query("select new StudentCourseMarkEntity(id,student,course) from StudentCourseMarkEntity where student=:student order by createdDate desc limit 1")
    StudentCourseMarkEntity findByLastMark(@Param("student") StudentEntity student);

    // 12
    @Query("select mark from StudentCourseMarkEntity where student=:student order by mark desc limit 3")
    List<Integer> findByStudent3BiggestMark(@Param("student") StudentEntity student);

    // 13
    @Query("select mark from StudentCourseMarkEntity where student=:student order by createdDate limit 1")
    Integer findByStudentFirstMark(@Param("student") StudentEntity student);

    // 14
    @Query("select mark from StudentCourseMarkEntity where student=:student and course=:course order by createdDate limit 1")
    Integer findByStudentCourseFirstMark(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 15
    @Query("select max(mark) from StudentCourseMarkEntity where student=:student and course=:course")
    Integer findByStudentCourseBiggestMark(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 16
    @Query("select AVG(mark) from StudentCourseMarkEntity where student=:student")
    Double findByStudentAVGMark(@Param("student") StudentEntity student);

    // 17
    @Query("select AVG(mark) from StudentCourseMarkEntity where student=:student and course=:course")
    Double findByStudentAndCourseAVGMark(@Param("student") StudentEntity student, @Param("course") CourseEntity course);

    // 18
    @Query("select count(*) from StudentCourseMarkEntity where student=:student and mark>:mark")
    Integer findByStudentCountMark(@Param("student") StudentEntity student, @Param("mark") Integer mark);

    // 19
    @Query("select max(mark) from StudentCourseMarkEntity where course=:course")
    Integer findByMaxMark(@Param("course") CourseEntity course);

    // 20
    @Query("select AVG(mark) from StudentCourseMarkEntity where course=:course")
    Double findByAvgMark(@Param("course") CourseEntity course);

    // 21
    @Query("select count(*) from StudentCourseMarkEntity where course=:course")
    Integer findByCountMark(@Param("course") CourseEntity course);


    // ================================================================================================
    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMark(@Param("studentId") Integer studentId);

    @Query(value = "SELECT c.id as cId, c.name as cName" +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
            "  c.id as cId, c.name as cName " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);

    Page<StudentCourseMarkEntity> findAllByStudentId(Integer studentId, Pageable paging);

    Page<StudentCourseMarkEntity> findAllByCourseId(Integer studentId, Pageable paging);
}
