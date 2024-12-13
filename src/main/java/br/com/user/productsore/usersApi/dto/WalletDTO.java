package br.com.user.productsore.usersApi.dto;

import br.com.user.productsore.usersApi.domain.user.User;
import jakarta.validation.constraints.NotNull;

public record WalletDTO(@NotNull User user, @NotNull Integer balance) {
}
