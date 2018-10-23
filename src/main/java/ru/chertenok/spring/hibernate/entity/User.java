package ru.chertenok.spring.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="username")
    private String userName;
    @Column(name = "enabled")
    private boolean isEnabled;
    @Column(name = "password")
    private String password;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List<UserRole> roleList;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", isEnabled=" + isEnabled +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
