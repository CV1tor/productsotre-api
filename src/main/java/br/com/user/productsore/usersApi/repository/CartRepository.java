package br.com.user.productsore.usersApi.repository;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT SUM(cp.quantity * p.price) FROM CartProduct cp JOIN Product p ON cp.product.id = p.id WHERE cp.cart.id = :id")
    Integer cartTotalPrice(UUID id);

    Optional<Cart> findByUserId(UUID id);

}
