package br.com.user.productsore.usersApi.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException() {
        super("Product is out of stock!");
    }

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
