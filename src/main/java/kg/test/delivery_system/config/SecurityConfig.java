package kg.test.delivery_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
//отключить защиту и спокойно тестировать API через Postman
@Configuration   //Это конфигурационный класс, используй его при запуске
public class SecurityConfig {

    @Bean  //Создай объект и добавь его в Spring контейнер
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  //Отключает CSRF защиту
                .authorizeHttpRequests(auth -> auth  //задаёшь правила доступа
                        .anyRequest().permitAll() //Разрешить ВСЕ запросы без проверки
                );

        return http.build();
    }
}