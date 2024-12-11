package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

public record ProductToCartDTO(@NotNull UUID productId, @Min(value = 1) Integer quantity) {

    public ProductToCartDTO(@NotNull UUID productId, @Min(value=1) Integer quantity) {
        this.productId = productId;
        this.quantity = quantity != null ? quantity : 1;
    }
}
