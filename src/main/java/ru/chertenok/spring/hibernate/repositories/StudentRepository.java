package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

    @Query(value = "select s.id,s.name, count(e.id)  q from student s " +
            "left join education e on e.student_id = s.id  group by s.id " +
            "order by count(e.id) DESC",nativeQuery = true)
    List<Object[]> findAllandCoursesCountSortCount();

    @Query(value = "select s.id,s.name, count(e.id)  q from student s " +
            "left join education e on e.student_id = s.id  group by s.id " +
            "order by s.name ASC",nativeQuery = true)
    List<Object[]> findAllandCoursesCountSortName();
}

