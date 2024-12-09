package br.com.user.productsore.usersApi.domain.user;

public enum UserRole {
    ADMIN("admin"),
    CUSTOMER("customer");

    final String role;

    UserRole(String role) {
        this.role = role;
    }
}
