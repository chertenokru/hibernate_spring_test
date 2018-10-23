package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chertenok.spring.hibernate.entity.Student;
import ru.chertenok.spring.hibernate.interfaces.StudentWithCoursesCount;
import ru.chertenok.spring.hibernate.services.StudentService;
import ru.chertenok.spring.hibernate.util.PageInfo;
import ru.chertenok.spring.hibernate.util.PaginationListFactory;

import java.util.List;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.util.Config.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final PaginationListFactory PAGINATION_LIST_STUDENT = new PaginationListFactory
            (new PageInfo("", ""), 1);
    private StudentService studentService;

    {
        PAGE_MAP.put(PagesName.studentList, new PageInfo("/student/list", "Список студентов", "student_list"));
        PAGE_MAP.put(PagesName.studentDetail, new PageInfo("/student/detail/{id}", "Информация о студенте", "student_detail", true));
        PAGE_MAP.put(PagesName.studentCourseEdit, new PageInfo("/student/{id}/edit_course", "Редактирование курсов студента", "education", true));
        PAGE_MAP.put(PagesName.studentCourseEditAdd, new PageInfo("/student/{id}/add_course/{id2}", "Добавить курс", "education", true));
        PAGE_MAP.put(PagesName.studentCourseEditRemove, new PageInfo("/student/{id}/remove_course/{id2}", "Удалить курс", "education", true));
    }


    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/list")
    public String studentsList(Model model,
                               @RequestParam(name = "sortCourse", required = false, defaultValue = "false") boolean sort,
                               @RequestParam(value = "page", defaultValue = "1") int page) {
        List<StudentWithCoursesCount> studentList = studentService.getStudentsList(sort, page - 1);
        model.addAttribute("studentList", studentList);
        model.addAttribute("studentDetailPageLink", PAGE_MAP.get(PagesName.studentDetail));

        model.addAttribute("page", page);
        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.studentList).getSHABLON());
        model.addAttribute("paginationList", PAGINATION_LIST_STUDENT.getList(studentService.getPageCount(), PAGE_MAP.get(PagesName.studentList)));
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public String studentDetailByID(Model model, @PathVariable int id) {

        Optional<Student> student = studentService.getStudentByID(id);
        if (!student.isPresent()) {
            model.addAttribute(MESSAGE404, "Студент с таким ID не найден");
            model.addAttribute("contentPage", PAGE_MAP.get(PagesName.page404).getSHABLON());
            model.addAttribute(BREADCRUMB,
                    new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList), PAGE_MAP.get(PagesName.page404)});
            return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
        }
        model.addAttribute("student", student.get());
        model.addAttribute("courseDetailPageLink", PAGE_MAP.get(PagesName.courseDetail));
        model.addAttribute("studentCourseEditPageLink", PAGE_MAP.get(PagesName.studentCourseEdit));
        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.studentDetail).getSHABLON());
        model.addAttribute(BREADCRUMB,
                new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList), PAGE_MAP.get(PagesName.studentDetail)});

        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping(path = "/{id}/edit_course", method = RequestMethod.GET)
    public String studentDetailByIDEdit(Model model, @PathVariable int id) {

        Optional<Student> student = studentService.getStudentByID(id);
        if (checkStudentIsPresent(model, student)) {
            model.addAttribute(BREADCRUMB,
                    new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList), PAGE_MAP.get(PagesName.page404)});
            return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
        }

        model.addAttribute("student", student.get());
        model.addAttribute("courseList", studentService.getCoursesByStudentID(id));
        model.addAttribute("courseListNew", studentService.getCoursesNotInStudentID(id));

        model.addAttribute("courseAddLink", PAGE_MAP.get(PagesName.studentCourseEditAdd));
        model.addAttribute("courseRemoveLink", PAGE_MAP.get(PagesName.studentCourseEditRemove));
        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.studentCourseEdit).getSHABLON());
        model.addAttribute(BREADCRUMB,
                new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList), PAGE_MAP.get(PagesName.studentDetail), PAGE_MAP.get(PagesName.studentCourseEdit)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping(path = "/{id_s}/add_course/{id_c}", method = RequestMethod.GET)
    public String studentAddCourseByID(Model model, @PathVariable int id_s, @PathVariable int id_c) {
        studentService.addCourseByID(id_s, id_c);
        return "redirect:" + PAGE_MAP.get(PagesName.studentCourseEdit).getURL_ReplaceID("" + id_s);
    }


    @RequestMapping(path = "/{id_s}/remove_course/{id_c}", method = RequestMethod.GET)
    public String studentRemoveCourseByID(Model model, @PathVariable int id_s, @PathVariable int id_c) {
      studentService.deleteCourseByID(id_s, id_c);
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


    private boolean checkStudentIsPresent(Model model, Optional<Student> student) {
        if (!student.isPresent()) {
            model.addAttribute(MESSAGE404, "Студент с таким ID не найден");
            model.addAttribute(BREADCRUMB,
                    new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.studentList), PAGE_MAP.get(PagesName.page404)});
            model.addAttribute("contentPage", PAGE_MAP.get(PagesName.page404).getSHABLON());
            return true;
        }
        return false;
    }
}
