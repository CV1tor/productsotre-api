package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDTO(@NotNull UUID id, @NotBlank @NotNull String username) {
}
