package br.com.users.app.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDto(
        UUID admId,

        @NotBlank(message = "This field cannot be null or blank!")
        String name,

        @NotBlank(message = "This field cannot be null or blank!")
        String email,

        @NotBlank(message = "This field cannot be null or blank!")
        String cpf,

        @NotBlank(message = "This field cannot be null or blank!")
        String password,

        @NotBlank(message = "This field cannot be null or blank!")
        String phone,

        @NotBlank(message = "This field cannot be null or blank!")
        String address,

        @NotBlank(message = "This field cannot be null or blank!")
        String street,

        @NotBlank(message = "This field cannot be null or blank!")
        String neighborhood,

        @NotBlank(message = "This field cannot be null or blank!")
        String complement,

        @NotBlank(message = "This field cannot be null or blank!")
        String number,

        @NotBlank(message = "This field cannot be null or blank!")
        String role
) {
}
