package ru.titov.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.titov.restaurant.repositories.MenuItemRepository;
import ru.titov.restaurant.repositories.DishCategoryRepository;
import ru.titov.restaurant.model.MenuItem;
import ru.titov.restaurant.model.DishCategory;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;
    private final DishCategoryRepository dishCategoryRepository;

    @Autowired
    public MenuService(MenuItemRepository menuItemRepository, DishCategoryRepository dishCategoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.dishCategoryRepository = dishCategoryRepository;
    }

    // Получение всех блюд
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Получение всех категорий блюд
    public List<DishCategory> getAllCategories() {
        return dishCategoryRepository.findAll();
    }


    // Получение блюд по категории
    public List<MenuItem> getMenuItemsByCategory(int categoryId) {
        Optional<DishCategory> category = dishCategoryRepository.findById(categoryId);
        return category.map(menuItemRepository::findByCategory).orElseThrow(
                () -> new IllegalArgumentException("Категория с ID " + categoryId + " не найдена")
        );
    }

    // Поиск блюда по имени
    public List<MenuItem> searchMenuItemsByName(String name) {
        return menuItemRepository.findByNameContainingIgnoreCase(name);
    }

    // Поиск блюда по ID
    public Optional<MenuItem> getMenuItemById(int id) {
        return menuItemRepository.findById(id);
    }

    // Добавление блюда
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // Обновление блюда
    public MenuItem updateMenuItem(int id, MenuItem updatedMenuItem) {
        Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id);
        if (existingMenuItem.isPresent()) {
            MenuItem menuItem = existingMenuItem.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setDescription(updatedMenuItem.getDescription());
            menuItem.setImageUrl(updatedMenuItem.getImageUrl());
            menuItem.setCategory(updatedMenuItem.getCategory());
            menuItem.setWeight(updatedMenuItem.getWeight());
            return menuItemRepository.save(menuItem);
        } else {
            throw new IllegalArgumentException("Блюдо с ID " + id + " не найдено.");
        }
    }

    // Удаление блюда
    public void deleteMenuItem(int id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Блюдо с ID " + id + " не найдено.");
        }
    }

}
