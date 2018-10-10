package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.chertenok.spring.hibernate.entity.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Integer> {

    class StudentResult{
        Student student;
        int courseCount;
    }

    @Query("select s.id,s.courses,s. from student s left join education e on s.id=e.student_id")
    public List<StudentResult> getAllStudentWithCourseCountOrderByCourseCount()
}
