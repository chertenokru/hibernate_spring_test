package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.interfaces.CoursesWithStudentCount;
import ru.chertenok.spring.hibernate.services.CourseService;
import ru.chertenok.spring.hibernate.util.PageInfo;
import ru.chertenok.spring.hibernate.util.PaginationListFactory;

import java.util.List;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.util.Config.*;

@Controller
@RequestMapping("/course")
public class CourseController {
    private static final PaginationListFactory PAGINATION_LIST_COURSE = new PaginationListFactory
            (new PageInfo("", ""), 1);
    private CourseService courseService;

    {
        PAGE_MAP.put(PagesName.courseList, new PageInfo("/course/list", "Список курсов", "course_list"));
        PAGE_MAP.put(PagesName.courseDetail, new PageInfo("/course/detail/{id}", "Информация о курсе", "course_detail", true));
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
            model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList), PAGE_MAP.get(PagesName.page404)});
            return PAGE_MAP.get(PagesName.page404).getSHABLON();
        }
        model.addAttribute("course", course.get());
        model.addAttribute("studentDetailPage", PAGE_MAP.get(PagesName.studentDetail));

        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList), PAGE_MAP.get(PagesName.courseDetail)});
        return PAGE_MAP.get(PagesName.courseDetail).getSHABLON();
    }


    @RequestMapping("/list")
    public String courseList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        List<CoursesWithStudentCount> courseList = courseService.findAllWidthStudentCount(page - 1);

        model.addAttribute("courseList", courseList);

        model.addAttribute("page", page);
        model.addAttribute("paginationList", PAGINATION_LIST_COURSE.getList(courseService.getPageCount(), PAGE_MAP.get(PagesName.courseList)));

        model.addAttribute("coursePage", PAGE_MAP.get(PagesName.courseDetail));
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList)});

        return PAGE_MAP.get(PagesName.courseList).getSHABLON();

    }

}
