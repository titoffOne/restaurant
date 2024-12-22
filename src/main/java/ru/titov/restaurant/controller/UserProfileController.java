package ru.titov.restaurant.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.titov.restaurant.model.UserRole;
import ru.titov.restaurant.services.UserService;
import ru.titov.restaurant.model.User;
import ru.titov.restaurant.model.UserStatus;

import java.security.Principal;


@Controller
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("title", "Регистрация");
        model.addAttribute("user", new ru.titov.restaurant.model.User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute ru.titov.restaurant.model.User user,
                               @RequestParam("role") String roleName,
                               Model model) {
        try {
            // Устанавливаем статус по умолчанию
            UserStatus defaultStatus = new UserStatus();
            defaultStatus.setId(2);
            user.setStatus(defaultStatus);

            UserRole userRole = new UserRole();
            if (roleName.equals("USER")) {
                userRole.setId(1);
            } else {
                userRole.setId(2);
            }
            user.setRole(userRole);

            userService.registerUser(user);
            model.addAttribute("message", "Пользователь успешно зарегистрирован!");
            return "login";  // Перенаправляем на страницу логина после регистрации
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";  // В случае ошибки возвращаем на страницу регистрации
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/profile";  // Перенаправление на профиль, если пользователь уже авторизован
        }
        model.addAttribute("title", "Вход");
        return "login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("title", "Мой профиль");

        return "profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("error", "Ошибка: пользователь не авторизован.");
        }
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if (userService.checkPassword(email, oldPassword)) {
            userService.updatePassword(email, newPassword);
            model.addAttribute("message", "Пароль успешно изменён.");
        } else {
            model.addAttribute("error", "Старый пароль неверен.");
        }
        model.addAttribute("user", user);
        return "profile";
    }

}
