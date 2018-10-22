package ru.chertenok.spring.hibernate.interfaces;

public interface CoursesWithStudentCount {
    long getId();

    String getDescription();

    int getLength();

    long getStudentCount();
}
