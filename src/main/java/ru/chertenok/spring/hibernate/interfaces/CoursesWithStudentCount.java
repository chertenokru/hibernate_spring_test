package ru.chertenok.spring.hibernate.interfaces;

import org.springframework.context.annotation.Bean;


public interface CoursesWithStudentCount {
    long getId();
    String getDescription();
    int getLength();
    long getStudentCount();
}
