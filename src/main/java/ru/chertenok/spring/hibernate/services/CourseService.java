package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.interfaces.CoursesWithStudentCount;
import ru.chertenok.spring.hibernate.repositories.CourseRepository;
import ru.chertenok.spring.hibernate.util.ResourceNotFound;

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

    @Transactional
    public Course getCourseyID_API(int id) {
        Optional<Course> cource = courseRepository.findById(id);
        if (cource.isPresent()) cource.get().getStudents().size();
        else throw new ResourceNotFound("Курс с id = " + id + " не нвйден");
        return cource.get();
    }


    public List<Course> findAllCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    public List<CoursesWithStudentCount> findAllWidthStudentCount(int pageNo) {

        List<CoursesWithStudentCount> list = courseRepository.findAllandCoursesCount(PageRequest.of(pageNo, pageSize));

        return list;
    }


    public long getPageCount(){
        return courseRepository.count()/pageSize+ ((courseRepository.count() % pageSize)>0?1:0);

    }
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }


    public Course update(Course theCourse) {
        if (courseRepository.existsById(theCourse.getId()))
           return  courseRepository.save(theCourse);
         else
            throw new ResourceNotFound("Курс с таким id = "+theCourse.getId()+" не найден");
    }

    public void deleteByID(int id){
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent())
             courseRepository.delete(course.get());
        else
            throw new ResourceNotFound("Курс с таким id = "+id+" не найден");

    }
}
