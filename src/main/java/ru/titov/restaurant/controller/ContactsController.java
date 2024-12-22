package ru.titov.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.titov.restaurant.model.Feedback;

@Controller
public class ContactsController {

    @GetMapping("/contacts")
    public String showFeedbackForm(Model model) {
        model.addAttribute("title", "Контакты");
        return "contacts";
    }
}
