package ru.chertenok.spring.hibernate.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.repositories.StudentRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
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


    @Transactional
    public List<Student> getStudentsList(boolean fullLoad) {
        List<Student> studentList = (List) studentRepository.findAll();
        if (fullLoad) {
            for (Student student : studentList) {
               int i =  student.getCourses().size();
            }
        }

        return studentList;
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




