package br.com.user.productsore.usersApi.domain.transaction;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer value;

    @OneToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;
}
