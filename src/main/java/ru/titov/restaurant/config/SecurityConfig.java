package ru.titov.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.titov.restaurant.repositories.UserRepository;
import ru.titov.restaurant.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Отключить CSRF для REST-запросов
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/profile").authenticated()  // Доступ к профилю только авторизованным пользователям
                    .anyRequest().permitAll()                               // Доступ к остальным страницам разрешён всем
            )
            .formLogin(form -> form
                    .loginPage("/login")                          // Кастомная страница логина
                    .defaultSuccessUrl("/profile", true)          // Перенаправление на профиль после успешного входа
                    .failureHandler((request, response, exception) -> {
                        if (exception instanceof LockedException) {
                            response.sendRedirect("/login?errorBlocked=true");
                        } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
                            response.sendRedirect("/login?error=true");
                        }
                    })
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")                       // URL для выхода
                    .logoutSuccessUrl("/login")                      // Куда перенаправить после выхода
                    .invalidateHttpSession(true)               // Инвалидировать сессию
                    .deleteCookies("JSESSIONID")               // Удалить куки сессии
                    .permitAll()
            )
            .sessionManagement(session -> session
                    .sessionFixation().newSession());

        return http.build();
    }

    // Разрешения для получния API-токена
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Отключить CSRF для REST-запросов
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/public/**").permitAll() // Открытые эндпоинты
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Эндпоинты для администратора
//                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // Настройка JWT
//                .formLogin(form -> form
//                        .loginPage("/login") // Кастомная страница логина
//                        .defaultSuccessUrl("/profile", true) // Перенаправление на профиль после успешного входа
//                        .failureHandler((request, response, exception) -> {
//                            if (exception instanceof LockedException) {
//                                response.sendRedirect("/login?errorBlocked=true");
//                            } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
//                                response.sendRedirect("/login?error=true");
//                            }
//                        })
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // URL для выхода
//                        .logoutSuccessUrl("/login") // Куда перенаправить после выхода
//                        .invalidateHttpSession(true) // Инвалидировать сессию
//                        .deleteCookies("JSESSIONID") // Удалить куки сессии
//                        .permitAll()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Сессии без сохранения состояния
//                );
//
//        return http.build();
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден с email: " + username));

            if (user.getStatus().getName().equals("blocked")) {
                throw new LockedException("Пользователь заблокирован.");
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().getName())
                    .build();
        };
    }

}
