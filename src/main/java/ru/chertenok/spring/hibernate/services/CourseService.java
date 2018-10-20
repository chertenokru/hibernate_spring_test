package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private int pageSize = 10;


    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    @Transactional
    public Optional<Course> getCourseyID(int id) {
        Optional<Course> cource = courseRepository.findById(id);
        if (cource.isPresent()) cource.get().getStudents().size();
        return cource;
    }

    public List<CoursesWithStudentCount> findAllWidthStudentCount(int pageNo) {

        List<CoursesWithStudentCount> list = courseRepository.findAllandCoursesCount(PageRequest.of(pageNo,pageSize));

        return list;
    }


    public long getPageCount(){
        return courseRepository.count()/pageSize+ ((courseRepository.count() % pageSize)>0?1:0);

    }
    public void daleteAll() {
        courseRepository.deleteAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
