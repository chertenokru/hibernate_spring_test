package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.interfaces.CoursesWithStudentCount;
import ru.chertenok.spring.hibernate.services.CourseService;
import ru.chertenok.spring.hibernate.util.Config;
import ru.chertenok.spring.hibernate.util.PageInfo;

import java.util.List;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.util.Config.*;
import static ru.chertenok.spring.hibernate.util.Config.BREADCRUMB;
import static ru.chertenok.spring.hibernate.util.Config.MESSAGE404;
import static ru.chertenok.spring.hibernate.util.Config.PAGE_MAP;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    {
       PAGE_MAP.put(PagesName.courseList, new PageInfo("/course/list","Список курсов","course_list"));
       PAGE_MAP.put(PagesName.courseDetail, new PageInfo("/course/detail/{id}","Информация о курсе","course_detail"));
    }


    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/detail/{id}")
    public String getCourseDetail(Model model, @PathVariable int id) {
        Optional<Course> course = courseService.getCourseyID(id);
        if (!course.isPresent()) {
            model.addAttribute(MESSAGE404, "Курс с таким id  не существует");
            model.addAttribute(BREADCRUMB, new PageInfo[] {PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.courseList),PAGE_MAP.get(PagesName.page404)});

            return PAGE_MAP.get(PagesName.page404).getSHABLON();
        }
        model.addAttribute("course", course.get());
        model.addAttribute(BREADCRUMB,new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.courseList),PAGE_MAP.get(PagesName.courseDetail)});
        return PAGE_MAP.get(PagesName.courseDetail).getSHABLON();
    }


    @RequestMapping("/list")
    public String courseList(Model model) {
        List<CoursesWithStudentCount> courseList = courseService.findAllWidthStudentCount();
        model.addAttribute("courseList", courseList);
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.courseList)});

        return PAGE_MAP.get(PagesName.courseList).getSHABLON();

    }

}
