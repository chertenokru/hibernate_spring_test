package ru.chertenok.spring.hibernate.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.interfaces.StudentWithCoursesCount;
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

           List<StudentWithCoursesCount> list =
                sortByCourseCount?
                        studentRepository.findAllandCoursesCountSortCount()
                        :studentRepository.findAllandCoursesCountSortName();
        return list;
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



}




