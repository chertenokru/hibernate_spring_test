package ru.chertenok.spring.hibernate.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<StudentWithCoursesCount> getStudentsList(boolean sortByCourseCount) {

        List<Object[]> list =
                sortByCourseCount?
                        studentRepository.findAllandCoursesCountSortCount()
                        :studentRepository.findAllandCoursesCountSortName();

        List<StudentWithCoursesCount> resList= new ArrayList<>();

        for (Object[] obj: list) {
            resList.add(new StudentWithCoursesCount((Integer)obj[0],(String)obj[1],(BigInteger)obj[2]));

        }
        return resList;
    }

    @Transactional
    public Optional<Student> getStudentByID(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) student.get().getCourses().size();
        return student;
    }

    @Transactional
    public List<Course> getCoursesByStudentID(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.isPresent() ? student.get().getCourses() : Collections.emptyList();
    }


   public static class StudentWithCoursesCount{
        private long id;
        private String Name;
        private long coursesCount;

        public StudentWithCoursesCount(long id, String name, BigInteger coursesCount) {
            this.id = id;
            Name = name;
            this.coursesCount=coursesCount.longValue();
        }

       public long getId() {
           return id;
       }

       public void setId(long id) {
           this.id = id;
       }

       public String getName() {
           return Name;
       }

       public void setName(String name) {
           Name = name;
       }

       public long getCoursesCount() {
           return coursesCount;
       }

       public void setCoursesCount(long coursesCount) {
           this.coursesCount = coursesCount;
       }
   }
}




