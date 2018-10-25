package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long>{

    public UserRole getByName(String name);
}
