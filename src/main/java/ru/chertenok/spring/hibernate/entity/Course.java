package ru.chertenok.spring.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Column(name="length")
    private int courseLength;

    @ManyToMany (mappedBy = "courses",fetch = FetchType.EAGER)
    private List<Student> students;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(int courseLength) {
        this.courseLength = courseLength;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
