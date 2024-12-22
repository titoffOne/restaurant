package ru.titov.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_statuses") // Таблица для хранения статусов
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 20) // Название статуса
    private String name;

    public UserStatus() {}

    public UserStatus(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
