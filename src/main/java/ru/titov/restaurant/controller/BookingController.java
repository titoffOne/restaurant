package ru.titov.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.titov.restaurant.model.Booking;
import ru.titov.restaurant.model.Feedback;
import ru.titov.restaurant.services.BookingService;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String showBookingForm(Model model) {
        model.addAttribute("title", "Бронирование");
        model.addAttribute("booking", new Booking());
        return "bookings";
    }

    // Обработка добавления бронирования
    @PostMapping("/bookings/new")
    public String addBooking(@ModelAttribute Booking booking) {
        bookingService.addBooking(booking);
        return "redirect:/bookings"; // Перенаправление на страницу со списком
    }

    @GetMapping("/bookings/all")
    public String showAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @PostMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings/all";
    }


}
