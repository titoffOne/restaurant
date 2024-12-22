package ru.titov.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.titov.restaurant.model.DishCategory;

//@Repository
@RepositoryRestResource(path = "dishCategories", collectionResourceRel = "dishCategories")
public interface DishCategoryRepository extends JpaRepository<DishCategory, Integer> {
}
