package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductOnCartDTO(@NotNull UUID id, @NotNull @NotBlank String name, @NotNull Integer price, @NotNull @Min(1) Integer quantity) {
}
