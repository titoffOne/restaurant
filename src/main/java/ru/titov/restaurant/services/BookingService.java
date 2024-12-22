package ru.titov.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.titov.restaurant.model.Booking;
import ru.titov.restaurant.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Добавление бронирования
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Получение всех бронирований
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Удаление бронирования по ID
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
