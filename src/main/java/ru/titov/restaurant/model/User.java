package ru.titov.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users") // Указывает имя таблицы
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для ID
    private int id;

    @NotBlank(message = "Имя обязательно")
    @Size(max = 50, message = "Имя должно быть не длиннее 50 символов")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Номер телефона обязателен")
    @Size(max = 20, message = "Номер телефона должен быть не длиннее 20 символов")
    @Column(nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "Электронная почта обязательна")
    @Email(message = "Введите корректный адрес электронной почты")
    @Size(max = 100, message = "Электронная почта должна быть не длиннее 100 символов")
    @Column(nullable = false, unique = true, length = 100) // Уникальное поле
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 100, message = "Пароль должен быть длиной от 8 до 100 символов")
    @Column(nullable = false, length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    public User() {}

    public User(String name, String phone, String email, String password, UserStatus status, UserRole role) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
