package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.interfaces.CoursesWithStudentCount;
import ru.chertenok.spring.hibernate.services.CourseService;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.util.PageInfo;
import ru.chertenok.spring.hibernate.util.PaginationListFactory;

import java.util.List;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.config.Config.*;

@Controller
@RequestMapping("/course")
public class CourseController {
    private static final PaginationListFactory PAGINATION_LIST_COURSE = new PaginationListFactory
            (new PageInfo("", ""), 1);
    private CourseService courseService;
    private PermissionService permissionService;

    static {
        // Добавляем права контроллера
        PERMISSION_MAP.put(PermissionName.courseList,
                new Permission("COURSE_VIEW_LIST", "COURSE", "Просмотр списка курсов"));
        PERMISSION_MAP.put(PermissionName.courseDetail,
                new Permission("COURSE_VIEW_DETAIL", "COURSE", "Просмотр детальной информации по курсу"));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.courseList, new PageInfo("/course/list", "Список курсов", "course_list",
                PERMISSION_MAP.get(PermissionName.courseList).getName()));
        PAGE_MAP.put(PagesName.courseDetail, new PageInfo("/course/detail/{id}", "Информация о курсе "
                , "course_detail", true, PERMISSION_MAP.get(PermissionName.courseDetail).getName()));
        //формируем  пути от страницы до корня, нормальные + ошибки, если есть обработка ошибок, чтоб вернуться на страницу назад
        BREADCRUMB_MAP.put(PagesName.courseList, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList)});
        BREADCRUMB_MAP.put(PagesName.mainShablon, new PageInfo[]{PAGE_MAP.get(PagesName.home)});
        BREADCRUMB_MAP.put(PagesName.courseDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList),
                PAGE_MAP.get(PagesName.courseDetail)});
        //для ошибок типа курс не существует
        BREADCRUMB_ERROR_MAP.put(PagesName.courseDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.courseList),
                PAGE_MAP.get(PagesName.page404)});

    }


    @Autowired
    public CourseController(PermissionService permissionService) {
        this.permissionService = permissionService;
        // проверяем наличие в бд и если нет, то заносим и даём права админу
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.courseList));
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.courseDetail));
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/detail/{id}")
    public String getCourseDetail(Model model, @PathVariable int id) {
        if (PageInfo.makeAllPlease(model, PagesName.courseDetail)) {
            Optional<Course> course = courseService.getCourseyID(id);
            if (!course.isPresent()) {
                model.addAttribute(MESSAGE404, "Курс с таким id  не существует");

                model.addAttribute(BREADCRUMB, BREADCRUMB_ERROR_MAP.get(PagesName.courseDetail));
            } else {
                // data
                model.addAttribute("course", course.get());
                model.addAttribute("studentDetailPageLink", PAGE_MAP.get(PagesName.studentDetail));
            }
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }


    @RequestMapping("/list")
    public String courseList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        if (PageInfo.makeAllPlease(model, PagesName.courseList))
        {
            List<CoursesWithStudentCount> courseList = courseService.findAllWidthStudentCount(page - 1);
            model.addAttribute("courseList", courseList);
            model.addAttribute("page", page);
            model.addAttribute("paginationList", PAGINATION_LIST_COURSE.getList(courseService.getPageCount(), PAGE_MAP.get(PagesName.courseList)));
            model.addAttribute("courseDetailPageLink", PAGE_MAP.get(PagesName.courseDetail));
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }
}
