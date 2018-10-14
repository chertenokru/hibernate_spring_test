package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.interfaces.CoursesWithStudentCount;
import ru.chertenok.spring.hibernate.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Optional<Course> getCourseyID(int id) {
        Optional<Course> cource = courseRepository.findById(id);
        if (cource.isPresent()) cource.get().getStudents().size();
        return cource;
    }

    public List<CoursesWithStudentCount> findAllWidthStudentCount() {

        List<CoursesWithStudentCount> list = courseRepository.findAllandCoursesCount();
        return list;
    }


    public void daleteAll() {
        courseRepository.deleteAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
