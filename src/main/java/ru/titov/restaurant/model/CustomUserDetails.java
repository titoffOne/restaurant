package ru.titov.restaurant.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // В данном случае возвращаем пустой список, но сюда можно добавить роли
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Возвращает пароль пользователя
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Возвращает логин пользователя (email)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно добавить логику для проверки истечения срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику для проверки блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Можно добавить логику для проверки истечения срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Можно добавить логику для проверки активности аккаунта
    }

    public User getUser() {
        return user; // Метод для получения исходного объекта User
    }
}
