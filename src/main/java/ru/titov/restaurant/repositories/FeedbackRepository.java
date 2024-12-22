package ru.titov.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.titov.restaurant.model.Feedback;

@RepositoryRestResource
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    // Метод для удаления отзыва по id (тип id - int)
    void deleteById(int id);
}
