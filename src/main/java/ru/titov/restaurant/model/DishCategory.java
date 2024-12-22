package ru.titov.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dish_categories") // Указывает имя таблицы
public class DishCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для int
    private int id;

    @NotBlank(message = "Название категории обязательно")
    @Size(max = 50, message = "Название категории должно быть не длиннее 50 символов")
    @Column(nullable = false, unique = true, length = 50) // Уникальность и ограничение длины
    private String name; // Название категории (например, "Салаты", "Супы", "Горячее")

    public DishCategory() {}

    public DishCategory(String name) {
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
