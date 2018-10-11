package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.repositories.CourseRepository;

import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Optional<Course> getCourseyID(int id)
    {
        return courseRepository.findById(id);
    }
}
