package br.com.user.productsore.usersApi.repository;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.cart.CartProduct;
import br.com.user.productsore.usersApi.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
    CartProduct findCartProductByProductAndCart(Product product, Cart cart);
}
