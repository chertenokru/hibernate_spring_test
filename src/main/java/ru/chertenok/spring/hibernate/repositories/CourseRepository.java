package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.chertenok.spring.hibernate.entity.Course;

public interface CourseRepository extends CrudRepository<Course,Long> {
}
