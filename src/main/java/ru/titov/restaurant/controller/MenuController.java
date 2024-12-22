package ru.titov.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.titov.restaurant.services.MenuService;
import ru.titov.restaurant.model.DishCategory;

import java.util.Optional;

@Controller
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Главная страница меню
    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("title", "Меню");
        model.addAttribute("menuItems", menuService.getAllMenuItems());
        model.addAttribute("categories", menuService.getAllCategories());
        return "menu";
    }

    // Страница меню по категории
    @GetMapping("/menu/{categoryId}")
    public String showMenuByCategory(@PathVariable int categoryId, Model model) {
        Optional<DishCategory> category = menuService.getAllCategories().stream()
                .filter(cat -> cat.getId() == categoryId)
                .findFirst();

        if (category.isEmpty()) {
            model.addAttribute("title", "Категория не найдена");
            model.addAttribute("errorMessage", "Категория с ID " + categoryId + " не найдена.");
            return "error";
        }

        model.addAttribute("title", "Меню - " + category.get().getName());
        model.addAttribute("menuItems", menuService.getMenuItemsByCategory(categoryId));
        model.addAttribute("categories", menuService.getAllCategories());
        return "menu";
    }

    // Поиск блюд по имени
    @GetMapping("/menu/search")
    public String searchMenu(@RequestParam("name") String name, Model model) {
        model.addAttribute("title", "Поиск блюда: " + name);
        model.addAttribute("menuItems", menuService.searchMenuItemsByName(name));
        model.addAttribute("categories", menuService.getAllCategories());
        return "menu";
    }

}
