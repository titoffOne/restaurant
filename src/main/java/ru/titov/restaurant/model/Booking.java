package ru.titov.restaurant.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int numberOfGuests;

    @Column(nullable = false)
    private LocalDateTime reservationDateTime;

    // Конструктор по умолчанию
    public Booking() {
    }

    // Конструктор с параметрами
    public Booking(String clientName, String phoneNumber, int numberOfGuests, LocalDateTime reservationDateTime) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.numberOfGuests = numberOfGuests;
        this.reservationDateTime = reservationDateTime;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }
}
