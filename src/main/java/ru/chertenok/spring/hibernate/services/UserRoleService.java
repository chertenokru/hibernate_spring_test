package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.User;
import ru.chertenok.spring.hibernate.entity.UserRole;
import ru.chertenok.spring.hibernate.repositories.UserRepository;
import ru.chertenok.spring.hibernate.repositories.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }




    public String getDefaultRole() {
        return "USER";
    }
    public String getAdminRole(){
        return "ADMIN";
    }

    public UserRole getUserRoleByName(String role){
      return  userRoleRepository.getByName(role);
    }

    public UserRole save(UserRole role) {
        userRoleRepository.save(role);
        return role;
    }
}
