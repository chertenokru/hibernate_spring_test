package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.User;
import ru.chertenok.spring.hibernate.entity.UserRole;
import ru.chertenok.spring.hibernate.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public List<UserRole> getUserRolesById(int id){
        return userRepository.getUserRoleList(id);
    }


    @Transactional
    public User newUser(String username, String password, String role) {
        List<UserRole> listUserRole = new ArrayList<UserRole>();
        UserRole userRole = userRoleService.getUserRoleByName(role);
        if (userRole != null) {
            listUserRole.add(userRole);
        }
        User user = new User(username, true, "{noop}" + password, listUserRole);
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public User newUser(String username, String password) {
        return newUser(username, password, userRoleService.getDefaultRole());
    }

    public Optional<User> getUserByName(String name){
        return userRepository.getUserByName(name);
    }
}
