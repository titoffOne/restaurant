package ru.titov.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.titov.restaurant.model.User;

import java.util.Optional;

//@Repository
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Integer> {
    // Метод для поиска пользователя по email
    Optional<User> findByEmail(String email);
}
