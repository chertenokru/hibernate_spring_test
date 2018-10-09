package ru.chertenok.spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Education;
import ru.chertenok.spring.hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Launcher {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.xml").buildSessionFactory();
        Session session = null;
        try {
            do {
                session = factory.getCurrentSession();
                session.beginTransaction();
                switch (showMenu()) {
                    case 4:
                        exit(0);
                    case 1: {
                        generateData(session);
                        break;
                    }
                    case 2: {
                        showStudents(session);
                        break;
                    }
                    case 3: {
                        showCourses(session);
                        break;
                    }
                }
                session.getTransaction().commit();
            }
             while (true);
        } finally {
            session.close();
            factory.close();
        }

    }

    private static void showStudents(Session session) {
            }

    private static void showCourses(Session session) {


    }

    private static void generateData(Session session) {
        //System.out.printf(" Удалено записей об обучении - %s\n ",session.createQuery("delete from Education").executeUpdate());
        System.out.printf(" Удалено записей о курсах - %s\n ",session.createQuery("delete from Course").executeUpdate());
        System.out.printf(" Удалено записей о студентах - %s\n ",session.createQuery("delete from Student").executeUpdate());
        scanner.nextLine();
        System.out.println("Укажите названия курсов через запятую (н-р: Химия,Физика,Вышка):");
        String[] courseName = (scanner.nextLine()).split(",");
        for (int i =0;i<courseName.length;i++){
            Course course = new Course();
            course.setDescription(courseName[i]);
            course.setCourseLength(random.nextInt(3)+3);
            session.save(course);
        }

        System.out.println("Укажите имена студентов через запятую(н-р:): Иванов,Петров,Сидоров");
        String[] studentName = (scanner.nextLine()).split(",");
        for (int i =0;i<studentName.length;i++){
            Student student = new Student();
            student.setName(studentName[i]);

            if (student.getCourses() == null)
                student.setCourses(new ArrayList<Course>());


            for (int j =0;j<(random.nextInt(courseName.length-1)+1);j++){
                Course course = (Course) session.createQuery("from Course where description =:desc").setParameter("desc", courseName[random.nextInt(courseName.length)]).list().get(0);

                student.getCourses().add(course);
               // session.save(education);
            }
            session.save(student);
        }


    }

    public static int showMenu() {

        System.out.println("\n1 - создать данные");
        System.out.println("2 - просмотр студентов");
        System.out.println("3 - просмотр курсов");
        System.out.println("4 - выход");
        return scanner.nextInt();


    }
}
