package br.com.users.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AdmDto(
        UUID id,

        @NotBlank(message = "This field cannot be null or blank!")
        String name,

        @NotBlank(message = "This field cannot be null or blank!")
        String email,

        @NotBlank(message = "This field cannot be null or blank!")
        String password,

        @NotBlank(message = "This field cannot be null or blank!")
        String role
) {
}
