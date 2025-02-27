package br.com.users.app.dto;

public record AuthenticationRequestDto(
        String email,
        String password
) {
}
