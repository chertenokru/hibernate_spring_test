package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.services.CourseService;

import java.util.Optional;

@Controller
@RequestMapping("/corse")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/course_detail/{id}")
    public String getCourseDetail(Model model, @PathVariable int id){
        Optional<Course> course = courseService.getCourseyID(id);
        if (!course.isPresent()){
            model.addAttribute("message","Курс с таким id  не существует");
            return "page404";
        }
        model.addAttribute("course",course.get());
        return "course_detail";

    }

}
