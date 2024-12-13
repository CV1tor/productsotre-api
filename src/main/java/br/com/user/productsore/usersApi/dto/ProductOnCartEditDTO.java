package br.com.user.productsore.usersApi.dto;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.product.Product;
import jakarta.validation.constraints.NotNull;

public record ProductOnCartEditDTO(@NotNull Product product, @NotNull Cart cart) {
}
