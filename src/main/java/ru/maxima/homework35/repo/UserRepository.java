package ru.maxima.homework35.repo;

import org.springframework.data.repository.CrudRepository;
import ru.maxima.homework35.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findUserByEmail(String email);
}
