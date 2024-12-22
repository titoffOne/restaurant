package ru.titov.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "feedback") // Указывает имя таблицы в PostgreSQL
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для int
    private int id;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно быть не длиннее 50 символов")
    @Column(nullable = false, length = 50) // Поле не может быть пустым, длина ограничена
    private String name;

    @NotBlank(message = "Электронная почта обязательна")
    @Email(message = "Введите корректный адрес электронной почты")
    @Column(nullable = false, unique = true) // Электронная почта должна быть уникальной
    private String email;

    @NotBlank(message = "Отзыв не должен быть пустым")
    @Size(max = 500, message = "Отзыв должен быть не длиннее 500 символов")
    @Column(nullable = false, length = 500) // Поле не может быть пустым, длина ограничена
    private String message;

    // Конструкторы, геттеры и сеттеры
    public Feedback() {}

    public Feedback(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
