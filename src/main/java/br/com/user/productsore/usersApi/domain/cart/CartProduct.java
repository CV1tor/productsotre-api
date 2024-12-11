package br.com.user.productsore.usersApi.domain.cart;

import br.com.user.productsore.usersApi.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "cart_product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    Integer quantity = 1;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;
}
