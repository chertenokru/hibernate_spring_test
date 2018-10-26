package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.entity.UserRole;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long>{

    public Permission getByName(String name);
}
