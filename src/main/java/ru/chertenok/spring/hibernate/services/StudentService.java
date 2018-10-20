package ru.chertenok.spring.hibernate.services;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.interfaces.StudentWithCoursesCount;
import ru.chertenok.spring.hibernate.repositories.CourseRepository;
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
    private CourseRepository courseRepository;
    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<StudentWithCoursesCount> getStudentsList(boolean sortByCourseCount,int pageNo) {

           List<StudentWithCoursesCount> list =
                sortByCourseCount?
                        studentRepository.findAllandCoursesCountSortCount(PageRequest.of(pageNo,pageSize))
                        :studentRepository.findAllandCoursesCountSortName(PageRequest.of(pageNo,pageSize));
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
        Optional<Student> student = getStudentByID(id);
        if (student.isPresent()) student.get().getCourses().size();
        return student.isPresent() ? student.get().getCourses() : Collections.emptyList();
    }

    @Transactional
    public List<Course> getCoursesNotInStudentID(int id) {
        return studentRepository.getCourseListNotInStudent(id);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Optional<Student> deleteCourseByID(int id_s, int id_c) {
        Optional<Student> student = studentRepository.findById(id_s);
        if (student.isPresent()) {
            for (Course course :student.get().getCourses()) {
                if (course.getId() == id_c){
                    student.get().getCourses().remove(course);
                    break;
                }
            }

            studentRepository.save(student.get());
        }
        return student;
    }

    @Transactional
    public Optional<Student> addCourseByID(int id_s, int id_c) {
        Optional<Student> student_o = studentRepository.findById(id_s);
        if (student_o.isPresent()) {
            Student student = student_o.get();
            Optional<Course> course_o = courseRepository.findById(id_c);
            if (course_o.isPresent()) {
                Course course = course_o.get();
                if (!student.getCourses().contains(course))
                student.getCourses().add(course);
                studentRepository.save(student);
            }
        }
        return student_o;
    }

    public long getPageCount(){
        return studentRepository.count()/pageSize+ ((studentRepository.count() % pageSize)>0?1:0);

    }

    public long getCount() {
        return studentRepository.count();
    }
}




