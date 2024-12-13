package br.com.user.productsore.usersApi.domain.product;

public enum ProductCategory {
    ELECTRONICS("electronics"),
    CLOTHING("clothing"),
    BOOKS("books"),
    TOYS("toys");

    final String category;

    ProductCategory(String category) {
        this.category = category;
    }
}
