package ru.chertenok.spring.hibernate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chertenok.spring.hibernate.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
