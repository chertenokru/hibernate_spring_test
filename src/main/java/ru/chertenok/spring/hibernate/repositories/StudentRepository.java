package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.chertenok.spring.hibernate.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
