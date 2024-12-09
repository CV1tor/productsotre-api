package br.com.user.productsore.usersApi.dto;

import br.com.user.productsore.usersApi.domain.product.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotNull
        @NotBlank
        String name,
        String description,
        @NotNull
        Integer price,
        @NotNull
        @Min(value = 1, message = "To add a product, the quantity must be greater or equal than 1.")
        Integer quantity,
        @NotNull
        ProductCategory category
) {
}
