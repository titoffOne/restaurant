package ru.titov.restaurant.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.titov.restaurant.model.MenuItem;
import ru.titov.restaurant.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    private final MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Получение всех блюд
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    // Получение блюда по ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable int id) {
        return menuService.getMenuItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Добавление нового блюда
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdItem = menuService.addMenuItem(menuItem);
        return ResponseEntity.ok(createdItem);
    }

    // Обновление существующего блюда
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {
        if (menuService.getMenuItemById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        menuItem.setId(id);
        MenuItem updatedItem = menuService.updateMenuItem(id, menuItem);
        return ResponseEntity.ok(updatedItem);
    }

    // Удаление блюда
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
        if (menuService.getMenuItemById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
