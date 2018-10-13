package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.interfaces.StudentWithCoursesCount;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "select s.id,s.name, count(e.id)  coursesCount from student s " +
            "left join education e on e.student_id = s.id  group by s.id " +
            "order by count(e.id) DESC", nativeQuery = true)
    List<StudentWithCoursesCount> findAllandCoursesCountSortCount();

    @Query(value = "select s.id,s.name, count(e.id)  coursesCount from student s " +
            "left join education e on e.student_id = s.id  group by s.id " +
            "order by s.name ASC", nativeQuery = true)
    List<StudentWithCoursesCount> findAllandCoursesCountSortName();


//    @Query(value = "select s.id,s.name, count(e.id)  coursesCount from student s " +
//            "left join education e on e.student_id = s.id  group by s.id "
//            ,nativeQuery = true)
//    List<StudentWithCoursesCount> findAllandCoursesCount(Sort sort);


}

