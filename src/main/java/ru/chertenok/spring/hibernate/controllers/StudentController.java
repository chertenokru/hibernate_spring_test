package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.interfaces.StudentWithCoursesCount;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.services.StudentService;
import ru.chertenok.spring.hibernate.util.PageInfo;
import ru.chertenok.spring.hibernate.util.PaginationListFactory;

import java.util.List;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.config.Config.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final String MESSAGE_STUDENT_NOT_FOUND = "Студент с таким ID не найден";
    private static final PaginationListFactory PAGINATION_LIST_STUDENT = new PaginationListFactory
            (new PageInfo("", ""), 1);

    static {
        // Добавляем права контроллера
        PERMISSION_MAP.put(PermissionName.studentList,
                new Permission("STUDENT_VIEW_LIST", "STUDENT", "Просмотр списка студентов"));
        PERMISSION_MAP.put(PermissionName.studentDetail,
                new Permission("STUDENT_VIEW_DETAIL", "STUDENT", "Просмотр информации о студенте"));
        PERMISSION_MAP.put(PermissionName.studentCourseEdit,
                new Permission("STUDENT_EDIT_COURSE_LIST", "STUDENT", "Редактирование курсов студентов"));
        PERMISSION_MAP.put(PermissionName.studentCourseEditAdd,
                new Permission("STUDENT_EDIT_COURSE_LIST_ADD", "STUDENT", "Добавление курсов студенту"));
        PERMISSION_MAP.put(PermissionName.studentCourseEditRemove,
                new Permission("STUDENT_EDIT_COURSE_LIST_REMOVE", "STUDENT", "Удаление курсов студенту"));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.studentList, new PageInfo("/student/list", "Список студентов", "student_list",
                PERMISSION_MAP.get(PermissionName.studentList).getName()));
        PAGE_MAP.put(PagesName.studentDetail, new PageInfo("/student/detail/{id}", "Информация о студенте",
                "student_detail", true, PERMISSION_MAP.get(PermissionName.studentDetail).getName()));
        PAGE_MAP.put(PagesName.studentCourseEdit, new PageInfo("/student/{id}/edit_course", "Редактирование курсов студента",
                "education", true, PERMISSION_MAP.get(PermissionName.studentCourseEdit).getName()));
        PAGE_MAP.put(PagesName.studentCourseEditAdd, new PageInfo("/student/{id}/add_course/{id2}", "Добавить курс",
                "education", true, PERMISSION_MAP.get(PermissionName.studentCourseEditAdd).getName()));
        PAGE_MAP.put(PagesName.studentCourseEditRemove, new PageInfo("/student/{id}/remove_course/{id2}", "Удалить курс",
                "education", true, PERMISSION_MAP.get(PermissionName.studentCourseEditRemove).getName()));
        //формируем  пути от страницы до корня, нормальные + ошибки, если есть обработка ошибок, чтоб вернуться на страницу назад
        BREADCRUMB_MAP.put(PagesName.studentList, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList)});
        BREADCRUMB_MAP.put(PagesName.studentDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.studentDetail)});
        BREADCRUMB_MAP.put(PagesName.studentCourseEdit, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.studentDetail), PAGE_MAP.get(PagesName.studentCourseEdit)});

        //для ошибок типа курс не существует, чтоб можно было вернуться к списку
        BREADCRUMB_ERROR_MAP.put(PagesName.studentDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.page404)});
        BREADCRUMB_ERROR_MAP.put(PagesName.studentCourseEdit, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.page404)});
        BREADCRUMB_ERROR_MAP.put(PagesName.studentCourseEditRemove, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.studentCourseEdit), PAGE_MAP.get(PagesName.page404)});
        BREADCRUMB_ERROR_MAP.put(PagesName.studentCourseEditAdd, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList),
                PAGE_MAP.get(PagesName.studentCourseEdit), PAGE_MAP.get(PagesName.page404)});

    }

    private StudentService studentService;
    private PermissionService permissionService;


    @Autowired
    public StudentController(PermissionService permissionService) {
        this.permissionService = permissionService;
        // проверяем наличие в бд и если нет, то заносим и даём права админу
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.studentList));
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.studentDetail));
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.studentCourseEdit));
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.studentCourseEditAdd));
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.studentCourseEditRemove));

    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/list")
    public String studentsList(Model model,
                               @RequestParam(name = "sortCourse", required = false, defaultValue = "false") boolean sort,
                               @RequestParam(value = "page", defaultValue = "1") int page) {
        if (PageInfo.makeAllPlease(model, PagesName.studentList)) {
            List<StudentWithCoursesCount> studentList = studentService.getStudentsList(sort, page - 1);
            model.addAttribute("studentList", studentList);
            model.addAttribute("studentDetailPageLink", PAGE_MAP.get(PagesName.studentDetail));
            model.addAttribute("page", page);
            model.addAttribute("paginationList", PAGINATION_LIST_STUDENT.getList(studentService.getPageCount(), PAGE_MAP.get(PagesName.studentList)));
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public String studentDetailByID(Model model, @PathVariable int id) {
        if (PageInfo.makeAllPlease(model, PagesName.studentDetail)) {
            Optional<Student> student = studentService.getStudentByID(id);
            if (!student.isPresent()) {
                model.addAttribute(MESSAGE404, MESSAGE_STUDENT_NOT_FOUND);
                model.addAttribute(BREADCRUMB, BREADCRUMB_ERROR_MAP.get(PagesName.studentDetail));
            } else {
                model.addAttribute("student", student.get());
                model.addAttribute("studentCourseEditPageLink", PAGE_MAP.get(PagesName.studentCourseEdit));
                model.addAttribute("courseDetailPageLink", PAGE_MAP.get(PagesName.courseDetail));
            }
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping(path = "/{id}/edit_course", method = RequestMethod.GET)
    public String studentDetailByIDEdit(Model model, @PathVariable int id) {

        if (PageInfo.makeAllPlease(model, PagesName.studentCourseEdit)) {
            Optional<Student> student = studentService.getStudentByID(id);
            if (!student.isPresent()) {
                model.addAttribute(MESSAGE404, MESSAGE_STUDENT_NOT_FOUND);
                model.addAttribute(BREADCRUMB, BREADCRUMB_ERROR_MAP.get(PagesName.studentDetail));
            } else {
                model.addAttribute("student", student.get());
                model.addAttribute("courseList", studentService.getCoursesByStudentID(id));
                model.addAttribute("courseListNew", studentService.getCoursesNotInStudentID(id));
                model.addAttribute("courseAddLink", PAGE_MAP.get(PagesName.studentCourseEditAdd));
                model.addAttribute("courseRemoveLink", PAGE_MAP.get(PagesName.studentCourseEditRemove));
            }
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }


    @RequestMapping(path = "/{id_s}/add_course/{id_c}", method = RequestMethod.GET)
    public String studentAddCourseByID(Model model, @PathVariable int id_s, @PathVariable int id_c) {
        if (PageInfo.makeAllPlease(model,PagesName.studentCourseEditAdd))
        {
            studentService.addCourseByID(id_s, id_c);
        } else {
            return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
        }
        return "redirect:" + PAGE_MAP.get(PagesName.studentCourseEdit).getURL_ReplaceID("" + id_s);
    }


    @RequestMapping(path = "/{id_s}/remove_course/{id_c}", method = RequestMethod.GET)
    public String studentRemoveCourseByID(Model model, @PathVariable int id_s, @PathVariable int id_c) {
        if (PageInfo.makeAllPlease(model,PagesName.studentCourseEditRemove)) {
            studentService.deleteCourseByID(id_s, id_c);
        } else{
            return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
        }
        return "redirect:" + PAGE_MAP.get(PagesName.studentCourseEdit).getURL_ReplaceID("" + id_s);
    }

 /*   @RequestMapping(path = "/{id}/edit", method = RequestMethod.GET)
    public String studentEditByID(Model model, @PathVariable int id, @RequestParam(name = "student", required = false) Student student) {

        Optional<Student> student_o = studentService.getStudentByID(id);
        if (!student_o.isPresent()) {
            model.addAttribute("message", "Студент с таким ID не найден");
            model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"},
                    {"Студент не найден", ""}});

            return "page404";
        }
        model.addAttribute("student", student_o.get());
        // model.addAttribute("courseList",studentService.getCoursesByStudentID(id));
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"}, {"Сведения о  студенте", ""}});
        return "student_detail";
    }
    */


}
