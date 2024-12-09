package br.com.user.productsore.usersApi.domain.product;

public enum ProductCategory {
    ELECTRONICS("eletronics"),
    CLOTHING("clothing"),
    BOOKS("books"),
    TOYS("toys");

    final String category;

    ProductCategory(String category) {
        this.category = category;
    }
}
