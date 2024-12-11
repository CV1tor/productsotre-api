package br.com.user.productsore.usersApi.dto;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.product.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartProductDTO (@NotNull Product product, @NotNull Cart cart, @Min(1) @NotNull Integer quantity) {
}
