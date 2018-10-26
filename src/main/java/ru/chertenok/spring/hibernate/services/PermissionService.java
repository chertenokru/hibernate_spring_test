package ru.chertenok.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.entity.UserRole;
import ru.chertenok.spring.hibernate.repositories.PermissionRepository;

@Service
public class PermissionService {
    private PermissionRepository permissionRepository;
    private UserRoleService userRoleService;

    public PermissionService() {
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setPermissionRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void registerPermission(Permission permission) {
        System.out.println("регистрация прав");

        Permission p = permissionRepository.getByName(permission.getName());
        if (p == null) {
            permissionRepository.save(permission);
            UserRole adminRole = userRoleService.getUserRoleByName(userRoleService.getAdminRole());
            adminRole.getPermissionList().add(permission);
            userRoleService.save(adminRole);
        }

    }
}
