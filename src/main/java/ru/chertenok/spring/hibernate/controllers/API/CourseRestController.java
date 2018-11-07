package ru.chertenok.spring.hibernate.controllers.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.services.CourseService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CourseRestController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseyID_API(id);
    }

    @GetMapping("/courses")
    public List<Course> getCoursesList() {
        return courseService.findAllCourses();
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course theCourse) {
        theCourse.setId(0);
        theCourse = courseService.save(theCourse);
        return theCourse;
    }

    @PutMapping( "/courses")
    public Course updateCourse(@RequestBody Course theCourse) {
        theCourse = courseService.update_API(theCourse);

        return theCourse;
    }

    @DeleteMapping("/courses/{id}")
    public int deleteStudent(@PathVariable int id) {
        courseService.deleteByID_API(id);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/courses")
    public int deleteStudents() {
        courseService.deleteAll_API();
        return HttpStatus.OK.value();
    }

}
