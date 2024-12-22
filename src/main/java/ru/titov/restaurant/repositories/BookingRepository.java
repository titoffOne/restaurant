package ru.titov.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.titov.restaurant.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
