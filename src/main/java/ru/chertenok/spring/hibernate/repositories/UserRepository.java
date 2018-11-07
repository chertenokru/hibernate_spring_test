package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.User;
import ru.chertenok.spring.hibernate.entity.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u.roleList from User u where u.id = :id")
    List<UserRole> getUserRoleList(@Param("id") int id);

    //@Query( "select u from User u where u. = :id")
    //List<UserRole> getUserRoleList(@Param("id") int id);

    Optional<User> getUserByName(String name);

}
