package kg.test.delivery_system.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.test.delivery_system.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Берём заголовок Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Если заголовка нет — пропускаем запрос дальше
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Вырезаем токен (убираем "Bearer ")
        String token = authHeader.substring(7);

        // 4. Достаём email из токена
        String email = jwtService.extractUsername(token);

        // 5. Если email есть и пользователь ещё не авторизован
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Загружаем пользователя из БД
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // 7. Проверяем токен
            if (jwtService.isTokenValid(token, userDetails)) {

                // 8. Создаём объект аутентификации
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 9. Сохраняем в контекст Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 10. Передаём запрос дальше
        filterChain.doFilter(request, response);
    }
}