package ru.chertenok.spring.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class UserRole {
    @Id
    @Column(name = "rolename")
    private String name;
    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    public UserRole() {
    }

    @Override
    public String toString() {
                return "UserRole{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
