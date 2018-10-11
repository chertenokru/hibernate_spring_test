package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.Course;
import ru.chertenok.spring.hibernate.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {



}
