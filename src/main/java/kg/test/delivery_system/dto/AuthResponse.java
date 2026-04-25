package kg.test.delivery_system.dto;

public record AuthResponse(
        String token,
        String email,
        String role
) {}