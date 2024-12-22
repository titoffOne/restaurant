package ru.titov.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menu_item") // Указывает имя таблицы
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для int
    private int id;

    @NotBlank(message = "Название блюда обязательно")
    @Size(max = 100, message = "Название блюда должно быть не длиннее 100 символов")
    @Column(nullable = false, length = 100) // Поле не может быть пустым, ограничение длины
    private String name;

    @Size(max = 500, message = "Описание должно быть не длиннее 500 символов")
    @Column(length = 500)
    private String description;

    @Size(max = 255, message = "URL изображения должен быть не длиннее 255 символов")
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DishCategory category;

    @Column(nullable = false)
    private int weight;

    public MenuItem() {}

    public MenuItem(String name, String description, String imageUrl, DishCategory category, int weight) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.weight = weight;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
