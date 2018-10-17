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
import ru.chertenok.spring.hibernate.services.CourseService;
import ru.chertenok.spring.hibernate.services.StudentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;


    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    @RequestMapping("/list")
    public String studentsList(Model model, @RequestParam(name = "sortCourse", required = false, defaultValue = "false") boolean sort) {
        List<StudentWithCoursesCount> studentList = studentService.getStudentsList(sort);
        model.addAttribute("studentList", studentList);
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", ""}});
        return "student_list";
    }


    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public String studentDetailByID(Model model, @PathVariable int id) {

        Optional<Student> student = studentService.getStudentByID(id);
        if (!student.isPresent()) {
            model.addAttribute("message", "Студент с таким ID не найден");
            return "page404";
        }
        model.addAttribute("student", student.get());
        // model.addAttribute("courseList",studentService.getCoursesByStudentID(id));
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"}, {"Сведения о  студенте", ""}});
        return "student_detail";
    }

    @RequestMapping(path = "/detail/{id}/edit", method = RequestMethod.GET)
    public String studentDetailByIDEdit(Model model, @PathVariable int id)
                                          {

        Optional<Student> student = studentService.getStudentByID(id);
        if (!student.isPresent()) {
            model.addAttribute("message", "Студент с таким ID не найден");
            return "page404";
        }
        model.addAttribute("student", student.get());
        model.addAttribute("courseList",studentService.getCoursesByStudentID(id));
        model.addAttribute("courseListNew",studentService.getCoursesNotInStudentID(id));
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"},
                {"Сведения о  студенте", "/student/detail/"+id},{"Редактирование курсов студента", ""}});
        return "education";
    }


    @RequestMapping(path = "/{id_s}/add_course/{id_c}", method = RequestMethod.GET)
    public String studentAddCourseByID(Model model, @PathVariable int id_s,@PathVariable int id_c)   {

        Optional<Student> student = studentService.addCourseByID(id_s,id_c);

        if (!student.isPresent()) {
            model.addAttribute("message", "Студент с таким ID не найден");
            return "page404";
        }

        model.addAttribute("student", student.get());
        model.addAttribute("courseList",studentService.getCoursesByStudentID(id_s));
        model.addAttribute("courseListNew",studentService.getCoursesNotInStudentID(id_s));
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"},
                {"Сведения о  студенте", "/student/detail/"+id_s},{"Редактирование курсов студента", ""}});
        return "education";
    }

    @RequestMapping(path = "/{id_s}/remove_course/{id_c}", method = RequestMethod.GET)
    public String studentRemoveCourseByID(Model model, @PathVariable int id_s,@PathVariable int id_c)   {

        Optional<Student> student = studentService.deleteCourseByID(id_s,id_c);

        if (!student.isPresent()) {
            model.addAttribute("message", "Студент с таким ID не найден");
            return "page404";
        }

        model.addAttribute("student", student.get());
        model.addAttribute("courseList",studentService.getCoursesByStudentID(id_s));
        model.addAttribute("courseListNew",studentService.getCoursesNotInStudentID(id_s));
        model.addAttribute("breadcrumb", new String[][]{{"Home", "/"}, {"Список студентов", "/student/list"},
                {"Сведения о  студенте", "/student/detail/"+id_s},{"Редактирование курсов студента", ""}});
        return "education";
    }


}
