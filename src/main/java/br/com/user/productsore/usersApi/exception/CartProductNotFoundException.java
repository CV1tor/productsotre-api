package br.com.user.productsore.usersApi.exception;

public class CartProductNotFoundException extends RuntimeException{
    public CartProductNotFoundException() {
        super("Não foi possível encontrar o produto dentro do carrinho");
    }

    public CartProductNotFoundException(String message) {
        super(message);
    }
}
