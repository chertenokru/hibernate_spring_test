package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataGenerateService {

    private StudentService studentService;
    private CourseService courseService;
    private static final String COURSE_NAME = "COURSE_";
    private static final String STUDENT_NAME = "STUDENT_";
    private  final Random random = new Random();

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Transactional
    public void generateData() {

        studentService.deleteAll();
        courseService.daleteAll();

        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(17)+3; i++) {
            Course course = new Course();
            course.setDescription(COURSE_NAME+i);
            course.setCourseLength(random.nextInt(3) + 3);
            course = courseService.save(course);
            courseList.add(course);
        }

        for (int i = 0; i < random.nextInt(17)+3; i++) {
            Student student = new Student();
            student.setName(STUDENT_NAME+i);

            if (student.getCourses() == null)
                student.setCourses(new ArrayList<Course>());


            for (int j = 0; j < (random.nextInt(courseList.size()-1) + 1); j++) {
                student.getCourses().add(courseList.get(random.nextInt(courseList.size())));
            }
            studentService.save(student);
        }

    }
}
