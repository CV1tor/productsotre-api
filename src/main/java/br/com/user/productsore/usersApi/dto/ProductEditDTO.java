package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductEditDTO(

        String name,
        String description,
        Integer price
) {
}
