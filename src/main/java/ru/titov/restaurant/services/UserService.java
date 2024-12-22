package ru.titov.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.titov.restaurant.repositories.UserRepository;
import ru.titov.restaurant.model.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Проверяем, существует ли уже пользователь с таким email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }

        // Хешируем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Сохраняем пользователя в базу данных
        return userRepository.save(user);
    }

    // Метод для аутентификации пользователя
    public User authenticateUser(String email, String password) {
        // Находим пользователя по email
        Optional<User> userOptional = userRepository.findByEmail(email);

        // Если пользователь не найден, возвращаем false
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с email " + email + " не найден"));
    }

    public boolean checkPassword(String email, String oldPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(oldPassword, user.getPassword());
        }
        return false;
    }

    public void updatePassword(String username, String rawNewPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Хэшируем пароль перед сохранением
            String encodedPassword = passwordEncoder.encode(rawNewPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }


}
