package ru.titov.restaurant.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.titov.restaurant.services.FeedbackService;
import ru.titov.restaurant.model.Feedback;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/reviews")
    public String showFeedbackForm(Model model) {
        model.addAttribute("title", "Отзывы");

        // Загрузка всех отзывов из базы данных через сервис
        model.addAttribute("reviews", feedbackService.getAllFeedbacks());

        model.addAttribute("feedback", new Feedback());
        return "reviews";
    }

    @PostMapping("/reviews")
    public String submitFeedbackForm(@Valid @ModelAttribute("feedback") Feedback feedback,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Отзывы");
            model.addAttribute("reviews", feedbackService.getAllFeedbacks()); // Загрузим отзывы снова при ошибках
            return "reviews"; // Вернемся к форме, если есть ошибки
        }

        // Сохранение отзыва в базе данных через сервис
        feedbackService.saveFeedback(feedback);
        return "thankYou";
    }

    @PostMapping("/reviews/delete")
    public String deleteFeedback(@RequestParam("id") int id) {
        feedbackService.deleteFeedback(id); // Удаление отзыва через сервис
        return "redirect:/reviews";
    }
}
