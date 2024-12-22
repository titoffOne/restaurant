package ru.titov.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.titov.restaurant.model.MenuItem;
import ru.titov.restaurant.model.DishCategory;

import java.util.List;

//@Repository
@RepositoryRestResource(path = "menuItems", collectionResourceRel = "menuItems")
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    // Поиск блюд по категории
    List<MenuItem> findByCategory(DishCategory category);

    // Поиск блюд по имени (частичное совпадение, без учета регистра)
    List<MenuItem> findByNameContainingIgnoreCase(String name);
}
