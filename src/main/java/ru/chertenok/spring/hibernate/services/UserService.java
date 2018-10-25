package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import ru.chertenok.spring.hibernate.entity.User;
import ru.chertenok.spring.hibernate.entity.UserRole;
import ru.chertenok.spring.hibernate.repositories.UserRepository;
import ru.chertenok.spring.hibernate.repositories.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public String getDefaultRole() {
        return "USER";
    }
    public String getAdminRole(){
        return "ADMIN";
    }

    @Transactional
    public User newUser(String username, String password, String role) {
        List<UserRole> listUserRole =  new ArrayList<UserRole>();
        listUserRole.add(userRoleRepository.getByName(role));
        User user = new User(username,true,"{noop}"+password,listUserRole);
        user = userRepository.save(user);
        return user;

    }
}
