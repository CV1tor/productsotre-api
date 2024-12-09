package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenDTO(@NotNull @NotBlank String token) {
}
