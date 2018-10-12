package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Optional<Course> getCourseyID(int id) {
        Optional<Course> cource = courseRepository.findById(id);
        if (cource.isPresent()) cource.get().getStudents().size();
        return cource;
    }

    public List<CoursesWithStudentCount> findAllWidthStudentCount() {

        List<Object[]> list = courseRepository.findAllandCoursesCount();


        List<CoursesWithStudentCount> resList = new ArrayList<>();

        for (Object[] obj : list) {
            resList.add(new CoursesWithStudentCount((Integer) obj[0], (String) obj[1], (int) obj[2], (BigInteger) obj[3]));
        }
        return resList;
    }


    public static class CoursesWithStudentCount {
        private long id;
        private String description;
        private int length;
        private long studentCount;

        public CoursesWithStudentCount(long id, String description, int length, BigInteger studentCount) {
            this.id = id;
            this.description = description;
            this.studentCount = studentCount.longValue();
            this.length = length;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public long getStudentCount() {
            return studentCount;
        }

        public void setStudentCount(long studentCount) {
            this.studentCount = studentCount;
        }
    }
}
