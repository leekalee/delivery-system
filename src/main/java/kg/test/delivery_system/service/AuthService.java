package kg.test.delivery_system.service;

import kg.test.delivery_system.dto.AuthResponse;
import kg.test.delivery_system.dto.LoginRequest;
import kg.test.delivery_system.dto.RegisterRequest;
import kg.test.delivery_system.entity.User;
import kg.test.delivery_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Проверяем что email не занят
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email уже зарегистрирован");
        }

        // Создаём пользователя
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(User.Role.ROLE_USER)
                .build();

        userRepository.save(user);

        // Генерируем токен
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole().name())
                        .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, user.getEmail(), user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        // Проверяем email и пароль
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        // Если дошли сюда — всё верно
        User user = userRepository.findByEmail(request.email())
                .orElseThrow();

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole().name())
                        .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, user.getEmail(), user.getRole().name());
    }
}