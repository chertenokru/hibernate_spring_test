package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer> {
    @Query(value = "select s.id,s.description,s.length, count(e.id)  q from course s "+
            "left join education e on e.course_id = s.id  group by s.id "
            ,nativeQuery = true)
    List<Object[]> findAllandCoursesCount();


}
