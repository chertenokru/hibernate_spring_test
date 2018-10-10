package ru.chertenok.spring.hibernate.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.repositories.StudentRepository;

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

    public List<Student> getStudentsList(){
        return (List<Student>)studentRepository.findAll();
    }

    public Optional<Student> getStudentByID(int id){
        return studentRepository.findById(id);
    }

    public List<Course> getCoursesByStudentID(int id){
        Optional<Student> student = studentRepository.findById(id);
            return student.isPresent()?student.get().getCourses():Collections.emptyList();
        }
    }

    public int getCoursesForStudent(int id){
    return studentRepository.
    }


